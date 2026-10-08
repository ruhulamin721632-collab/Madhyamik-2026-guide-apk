package com.example.data.api

import android.util.Log
import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class GeminiTeacherService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    suspend fun askAiTeacher(userPrompt: String, subjectContext: String = ""): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }

        if (apiKey.isNullOrBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext getOfflineTeacherResponse(userPrompt, subjectContext)
        }

        val systemInstruction = """
            You are "StudyMate AI Teacher", an expert, kind and motivating West Bengal Board (WBBSE) Class 10 Madhyamik 2027 Mentor.
            Guidelines:
            1. Language: Answer in the student's preferred language (Bengali, English, or simple friendly Hinglish). If asked in Bengali, use correct West Bengal academic terminology.
            2. Mathematics: Provide school-style step-by-step solutions with clear formulas and steps.
            3. 5-Mark Answers: Structure long answers with:
               - Introduction (ভূমিকা)
               - Main Points (মুখ্য বিষয়)
               - Explanation / Diagram hints (ব্যাখ্যা)
               - Conclusion (উপসংহার)
            4. 1-Mark & 3-Mark: Keep 1-mark answers direct and concise. Keep 3-mark answers to 3 clear distinct points.
            5. Encourage consistency and calm confidence: "Consistency today = confidence in Madhyamik 2027!"
            6. Clearly label this output as: "[AI-Generated Mentor Guidance - Verify with your textbook/teacher]".
        """.trimIndent()

        val fullPrompt = if (subjectContext.isNotBlank()) {
            "Subject Context: $subjectContext\n\nStudent Question: $userPrompt"
        } else {
            userPrompt
        }

        try {
            val jsonBody = JSONObject().apply {
                val contentsArray = JSONArray().apply {
                    val contentObj = JSONObject().apply {
                        val partsArray = JSONArray().apply {
                            put(JSONObject().apply { put("text", "$systemInstruction\n\n$fullPrompt") })
                        }
                        put("parts", partsArray)
                    }
                    put(contentObj)
                }
                put("contents", contentsArray)

                val genConfig = JSONObject().apply {
                    put("temperature", 0.7)
                    put("topP", 0.95)
                    put("maxOutputTokens", 1500)
                }
                put("generationConfig", genConfig)
            }

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val requestBody = jsonBody.toString().toRequestBody(mediaType)

            val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            if (response.isSuccessful && responseBody != null) {
                val jsonResponse = JSONObject(responseBody)
                val candidates = jsonResponse.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val firstCandidate = candidates.getJSONObject(0)
                    val content = firstCandidate.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    if (parts != null && parts.length() > 0) {
                        return@withContext parts.getJSONObject(0).optString("text")
                    }
                }
            }

            Log.w("GeminiTeacherService", "API call unsuccessful or empty, using fallback: ${response.code}")
            return@withContext getOfflineTeacherResponse(userPrompt, subjectContext)
        } catch (e: Exception) {
            Log.e("GeminiTeacherService", "Error calling Gemini API", e)
            return@withContext getOfflineTeacherResponse(userPrompt, subjectContext)
        }
    }

    private fun getOfflineTeacherResponse(prompt: String, subjectContext: String): String {
        val lower = prompt.lowercase()
        return when {
            lower.contains("math") || lower.contains("অংক") || lower.contains("solve") || lower.contains("সমীকরণ") -> {
                """
                [StudyMate AI Teacher - WBBSE 2027 Guide]
                
                📐 গণিত সমাধান পদ্ধতি (Step-by-Step Method):
                
                ১. প্রদত্ত তথ্য চিহ্নিতকরণ: প্রশ্নে কী কী মান দেওয়া আছে তা ডানপাশে স্পষ্ট করে লিখে নাও (যেমন: আসল P, সুদের হার r, সময় t)।
                ২. প্রয়োজনীয় সূত্র নির্বাচন: মাধ্যমিক পরীক্ষার জন্য সূত্রটি সরাসরি বক্সে লেখো:
                   I = (P × r × t) / 100 অথবা ax² + bx + c = 0
                ৩. নির্ভুল গণনা: প্রতিটি ধাপ কাটাকাটি না করে নিচে নিচে সমাধান করো।
                ৪. একক উল্লেখ: উত্তরের শেষে টাকা/বছর/বর্গসেমি একক লিখতে কখনোই ভুলবে না।
                
                💡 টিপস: দ্বিঘাত সমীকরণের ক্ষেত্রে সর্বদা নিরূপক (b² - 4ac) পরীক্ষা করে বীজের প্রকৃতি যাচাই করবে।
                """.trimIndent()
            }
            lower.contains("5 mark") || lower.contains("৫ নম্বর") || lower.contains("structure") || lower.contains("কিভাবে লিখব") -> {
                """
                [StudyMate AI Teacher - Exam Presentation]
                
                📝 মাধ্যমিকে ৫ নম্বরের উত্তরের আদর্শ ৪-ধাপের কাঠামো:
                
                ১. ভূমিকা (Introduction):
                   প্রশ্নের প্রেক্ষাপট ও মূল তত্ত্ব নিয়ে ২-৩ লাইনের সংক্ষিপ্ত সূচনা।
                ২. মূল বক্তব্য / কারণসমূহ (Main Points):
                   কমপক্ষে ৩ থেকে ৪টি স্পষ্ট সাব-হেডিং পয়েন্ট আকারে লেখো (যেমন: ১. ভৌগোলিক অবস্থান, ২. অনুকূল জলবায়ু, ৩. কাঁচামালের প্রাচুর্য)।
                ৩. বিশদ ব্যাখ্যা ও প্রাসঙ্গিক উদাহরণ:
                   প্রতিটি পয়েন্টে একটি করে বিজ্ঞানসম্মত তথ্য ও বাস্তব উদাহরণ দাও।
                ৪. চিত্র / সূত্র / উপসংহার (Conclusion):
                   জীবনবিজ্ঞান ও ভূগোলে পেন্সিল দিয়ে পাশে বক্স করে চিত্র আঁকবে। শেষে ১ লাইনের সারসংক্ষেপ।
                   
                ⭐ মাধ্যমিক খাতার গোল্ডেন রুল: পরীক্ষক পুরো প্যারাগ্রাফ পড়েন না, পয়েন্ট ও কী-ওয়ার্ডস খোঁজেন!
                """.trimIndent()
            }
            lower.contains("easy") || lower.contains("samjhao") || lower.contains("সহজ") -> {
                """
                [StudyMate AI Teacher - সহজ ভাষায় ব্যাখ্যা]
                
                বন্ধু, যেকোনো কঠিন বিষয় সহজে বোঝার ফর্মুলা হলো:
                
                ১. বাস্তব জীবনের সাথে তুলনা করো:
                   যেমন—ভোল্টেজ হলো জলের ট্যাঙ্কের উচ্চতা (চাপ), আর বিদ্যুৎ প্রবাহ হলো পাইপ দিয়ে জল পড়ার গতি!
                ২. ছোট ছোট অংশে ভাগ করো:
                   পুরো চ্যাপ্টার একদিনে পড়ার চেষ্টা না করে প্রতিদিন ১টি করে সাবটপিক আয়ত্ত করো।
                ৩. বন্ধ চোখের রিভিশন:
                   পড়ার পর বই বন্ধ করে ২ মিনিট মনে করো তুমি কাউকে এটা শেখাচ্ছ।
                
                "আজকের ১ ঘণ্টা নিবিড় পরিশ্রম = ২০২৭ মাধ্যমিকে ৯৫% আত্মবিশ্বাস!"
                """.trimIndent()
            }
            else -> {
                """
                [StudyMate AI Teacher - Madhyamik 2027 Mentor]
                
                নমস্কার! তোমার প্রশ্নটি পেয়েছি।
                
                🎯 তোমার লক্ষ্য ২০২৭ সালের মাধ্যমিক পরীক্ষায় শ্রেষ্ঠ ফলাফল অর্জন করা:
                • আজকের পড়ার লক্ষ্য: দৈনিক রুটিন অনুযায়ী অধ্যায় শেষ করো।
                • রিভিশন: আজ যা পড়েছ, আগামী ২৪ ঘণ্টার মধ্যে একবার ১০ মিনিটের জন্য ঝালিয়ে নাও।
                • অবজেক্টিভ প্র্যাকটিস: প্রতিদিন টেস্ট বিভাগে ১০টি করে MCQ সমাধান করো।
                
                যেকোনো নির্দিষ্ট প্রশ্নের ৫ নম্বরের উত্তর, অংকের স্টেপ-বাই-স্টেপ সমাধান বা বিজ্ঞানের চিত্র বুঝতে আমাকে বিস্তারিত প্রশ্ন লিখে পাঠাও!
                """.trimIndent()
            }
        }
    }
}
