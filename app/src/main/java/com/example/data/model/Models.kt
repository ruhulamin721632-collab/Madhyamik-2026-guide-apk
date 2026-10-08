package com.example.data.model

import androidx.compose.ui.graphics.Color

enum class SubjectType(
    val id: String,
    val nameBn: String,
    val nameEn: String,
    val colorHex: Long,
    val totalMarks: Int = 90,
    val description: String
) {
    BENGALI(
        "bengali",
        "বাংলা (প্রথম ভাষা)",
        "Bengali",
        0xFFE11D48,
        90,
        "গল্প, কবিতা, নাটক, প্রবন্ধ, ব্যাকরণ ও নির্মিতি"
    ),
    ENGLISH(
        "english",
        "English (Second Language)",
        "English",
        0xFF2563EB,
        90,
        "Reading Comprehension (Seen & Unseen), Grammar & Writing"
    ),
    MATHEMATICS(
        "math",
        "গণিত (Mathematics)",
        "Mathematics",
        0xFF7C3AED,
        90,
        "পাটিগণিত, বীজগণিত, জ্যামিতি, পরিমিতি ও ত্রিকোণমিতি"
    ),
    PHYSICAL_SCIENCE(
        "phy_sci",
        "ভৌতবিজ্ঞান (Physical Science)",
        "Physical Science",
        0xFF0D9488,
        90,
        "পরিবেশ, পদার্থবিদ্যা ও রসায়নবিদ্যা"
    ),
    LIFE_SCIENCE(
        "life_sci",
        "জীবনবিজ্ঞান (Life Science)",
        "Life Science",
        0xFF16A34A,
        90,
        "নিয়ন্ত্রণ ও সমন্বয়, বংশগতি, অভিব্যক্তি ও পরিবেশ"
    ),
    HISTORY(
        "history",
        "ইতিহাস (History)",
        "History",
        0xFFD97706,
        90,
        "ইতিহাসের ধারণা, সংস্কার, আন্দোলন, উপনিবেশ ও জাতীয়তাবাদ"
    ),
    GEOGRAPHY(
        "geography",
        "ভূগোল (Geography)",
        "Geography",
        0xFF0284C7,
        90,
        "প্রাকৃতিক ভূগোল, পরিবেশ ও ভারতের প্রাকৃতিক ও অর্থনৈতিক ভূগোল"
    );

    val composeColor: Color get() = Color(colorHex)
}

data class Chapter(
    val id: String,
    val subjectId: String,
    val chapterNumber: Int,
    val titleBn: String,
    val titleEn: String,
    val marksWeightage: String,
    val summary: String,
    val keyTopics: List<String>,
    var isCompleted: Boolean = false,
    var revisionCount: Int = 0,
    var lastRevised: String = ""
)

enum class QuestionType {
    MCQ,
    VSA, // Very Short Answer (1 mark)
    SA_2, // Short Answer (2 marks)
    SA_3, // Medium Answer (3 marks)
    LA_5, // Long Structured Answer (5 marks)
    DIFFERENCE,
    DEFINITION,
    MAP_POINTING
}

enum class Difficulty {
    EASY, MEDIUM, HARD
}

data class StructuredAnswer(
    val directAnswer: String,
    val introduction: String = "",
    val points: List<String> = emptyList(),
    val explanation: String = "",
    val conclusion: String = "",
    val diagramOrFormulaHint: String = ""
)

data class Question(
    val id: String,
    val subjectId: String,
    val chapterId: String,
    val chapterTitle: String,
    val questionBn: String,
    val questionEn: String = "",
    val marks: Int,
    val type: QuestionType,
    val difficulty: Difficulty,
    val structuredAnswer: StructuredAnswer,
    val isVeryImportant: Boolean = false,
    val isOfficialPYQ: Boolean = false, // Previous Years Question style
    val isAiGenerated: Boolean = false,
    val pyqYear: String? = null,
    val mcqOptions: List<String> = emptyList(),
    val correctMcqIndex: Int = -1,
    var isInRevision: Boolean = false,
    var isBookmarked: Boolean = false
)

data class StudySession(
    val id: String,
    val slotName: String, // Morning, Afternoon, Evening, Night
    val timeLabel: String,
    val subjectId: String,
    val subjectName: String,
    val chapterName: String,
    val durationMinutes: Int,
    var isCompleted: Boolean = false,
    var isMissed: Boolean = false
)

data class AZGuideItem(
    val letter: Char,
    val title: String,
    val bengaliTitle: String,
    val summary: String,
    val detailedStrategy: String,
    val actionChecklist: List<String>
)

data class Flashcard(
    val id: String,
    val subjectId: String,
    val chapterName: String,
    val front: String,
    val back: String,
    val tag: String // Formula, Definition, Year, Principle
)

data class StudentNote(
    val id: String,
    val subjectId: String,
    val chapterTitle: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val isBookmarked: Boolean = false
)

data class MockTestQuestion(
    val id: String,
    val question: Question,
    var selectedOptionIndex: Int = -1,
    var isMarkedForReview: Boolean = false
)

data class MockTestResult(
    val testTitle: String,
    val subjectId: String,
    val totalQuestions: Int,
    val correctCount: Int,
    val wrongCount: Int,
    val skippedCount: Int,
    val scorePercentage: Int,
    val timeTakenSeconds: Long,
    val weakChapters: List<String>,
    val timestamp: Long = System.currentTimeMillis()
)

data class ReadinessReport(
    val overallScore: Int, // 0 - 100%
    val syllabusPercent: Int,
    val revisionScore: Int,
    val mockScore: Int,
    val consistencyScore: Int,
    val readinessStatus: String, // "Strongly on Track", "Good Progress", "Needs Urgent Push"
    val strongSubjects: List<String>,
    val weakSubjects: List<String>,
    val urgentNextSteps: List<String>,
    val suggestedWeeklyPlan: String
)

data class ChatMessage(
    val id: String,
    val isUser: Boolean,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isVerifiedMaterial: Boolean = false,
    val suggestedActions: List<String> = emptyList()
)
