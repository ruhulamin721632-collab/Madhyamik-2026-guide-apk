package com.example.data.repository

import android.content.Context
import com.example.data.api.GeminiTeacherService
import com.example.data.local.*
import com.example.data.model.*
import com.example.receiver.StudyAlarmManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import java.util.Calendar

class MadhyamikRepository(
    private val context: Context,
    private val dao: MadhyamikDao,
    private val geminiService: GeminiTeacherService = GeminiTeacherService()
) {

    fun getSubjects(): List<SubjectType> = SyllabusData.subjects

    fun getChapters(subjectId: String? = null): List<Chapter> {
        return if (subjectId == null) {
            SyllabusData.chapters
        } else {
            SyllabusData.chapters.filter { it.subjectId == subjectId }
        }
    }

    fun getQuestions(
        subjectId: String? = null,
        marks: Int? = null,
        difficulty: Difficulty? = null,
        type: QuestionType? = null
    ): List<Question> {
        return SyllabusData.questions.filter { q ->
            (subjectId == null || q.subjectId == subjectId) &&
            (marks == null || q.marks == marks) &&
            (difficulty == null || q.difficulty == difficulty) &&
            (type == null || q.type == type)
        }
    }

    fun getAZGuide(): List<AZGuideItem> = SyllabusData.azGuideList

    fun getFlashcards(subjectId: String? = null): List<Flashcard> {
        return if (subjectId == null) {
            SyllabusData.flashcards
        } else {
            SyllabusData.flashcards.filter { it.subjectId == subjectId }
        }
    }

    // Room DB operations
    fun getStudentProfile(): Flow<StudentProfileEntity?> = dao.getStudentProfile()

    suspend fun saveStudentProfile(profile: StudentProfileEntity) {
        dao.saveStudentProfile(profile)
    }

    fun getChapterProgress(): Flow<List<ChapterProgressEntity>> = dao.getAllChapterProgress()

    suspend fun markChapterCompleted(chapterId: String, subjectId: String, completed: Boolean) {
        val existing = dao.getAllChapterProgress().firstOrNull()?.find { it.chapterId == chapterId }
        val revisionCount = if (completed) (existing?.revisionCount ?: 0) + 1 else (existing?.revisionCount ?: 0)
        dao.updateChapterProgress(
            ChapterProgressEntity(
                chapterId = chapterId,
                subjectId = subjectId,
                isCompleted = completed,
                revisionCount = revisionCount,
                lastRevisedTimestamp = System.currentTimeMillis()
            )
        )

        if (completed) {
            // Schedule spaced repetition in queue (1 day, 3 days, 7 days)
            scheduleSpacedRevisionsForChapter(chapterId, subjectId)
        }
    }

    private suspend fun scheduleSpacedRevisionsForChapter(chapterId: String, subjectId: String) {
        val chapter = SyllabusData.chapters.find { it.id == chapterId }
        val title = chapter?.titleBn ?: "Chapter Revision"
        val now = System.currentTimeMillis()
        val oneDayMillis = 24 * 60 * 60 * 1000L

        // Day 1
        dao.insertRevision(
            RevisionQueueEntity(
                id = "${chapterId}_rev_d1",
                itemType = "CHAPTER",
                subjectId = subjectId,
                title = "$title (Day 1 Review)",
                scheduledDate = now + oneDayMillis,
                intervalStage = 1
            )
        )
        // Day 3
        dao.insertRevision(
            RevisionQueueEntity(
                id = "${chapterId}_rev_d3",
                itemType = "CHAPTER",
                subjectId = subjectId,
                title = "$title (Day 3 Deep Revision)",
                scheduledDate = now + (3 * oneDayMillis),
                intervalStage = 3
            )
        )
        // Day 7
        dao.insertRevision(
            RevisionQueueEntity(
                id = "${chapterId}_rev_d7",
                itemType = "CHAPTER",
                subjectId = subjectId,
                title = "$title (Day 7 Master Recall)",
                scheduledDate = now + (7 * oneDayMillis),
                intervalStage = 7
            )
        )
    }

    fun getPendingRevisions(): Flow<List<RevisionQueueEntity>> = dao.getPendingRevisions()

    suspend fun completeRevision(id: String) {
        dao.markRevisionCompleted(id)
    }

    fun getAllNotes(): Flow<List<StudentNoteEntity>> = dao.getAllNotes()

    suspend fun addNote(note: StudentNoteEntity) = dao.insertNote(note)

    suspend fun deleteNote(noteId: String) = dao.deleteNote(noteId)

    fun getTestHistory(): Flow<List<TestHistoryEntity>> = dao.getTestHistory()

    suspend fun recordTestResult(result: TestHistoryEntity) = dao.insertTestResult(result)

    fun getBookmarks(): Flow<List<QuestionBookmarkEntity>> = dao.getAllBookmarks()

    suspend fun toggleBookmark(questionId: String, isBookmarked: Boolean) {
        if (isBookmarked) {
            dao.addBookmark(QuestionBookmarkEntity(questionId))
        } else {
            dao.removeBookmark(questionId)
        }
    }

    // AI Teacher
    suspend fun queryAiTeacher(prompt: String, subjectContext: String = ""): String {
        return geminiService.askAiTeacher(prompt, subjectContext)
    }

    // Smart Daily Study Planner & Alarms generator
    fun generateDailyStudyPlan(profile: StudentProfileEntity?): List<StudySession> {
        val targetHours = profile?.dailyStudyHours ?: 4.0f
        return listOf(
            StudySession(
                id = "slot_1",
                slotName = "Morning Focus",
                timeLabel = "06:30 AM — 07:15 AM",
                subjectId = "math",
                subjectName = "Mathematics (গণিত)",
                chapterName = "একচলবিশিষ্ট দ্বিঘাত সমীকরণ (বোর্ড সমীকরণ গঠন)",
                durationMinutes = 45
            ),
            StudySession(
                id = "slot_2",
                slotName = "Afternoon Concepts",
                timeLabel = "04:30 PM — 05:15 PM",
                subjectId = "phy_sci",
                subjectName = "Physical Science (ভৌতবিজ্ঞান)",
                chapterName = "গ্যাসের আচরণ (বয়েল ও চার্লসের গাণিতিক অংক)",
                durationMinutes = 45
            ),
            StudySession(
                id = "slot_3",
                slotName = "Evening Deep Study",
                timeLabel = "06:30 PM — 07:30 PM",
                subjectId = "life_sci",
                subjectName = "Life Science (জীবনবিজ্ঞান)",
                chapterName = "জীবজগতের নিয়ন্ত্রণ (অক্ষিগোলক ও নিউরোন ৫ নম্বর চিত্র)",
                durationMinutes = 60
            ),
            StudySession(
                id = "slot_4",
                slotName = "Night Revision & MCQ",
                timeLabel = "09:30 PM — 10:15 PM",
                subjectId = "history",
                subjectName = "History & Bengali",
                chapterName = "সংস্কার ও বহুরূপী (দৈনিক ১০টি MCQ ও VSA অনুশীলন)",
                durationMinutes = 45
            )
        )
    }

    // Schedule Android Alarms for all sessions
    fun scheduleAllDailyAlarms(sessions: List<StudySession>) {
        sessions.forEachIndexed { index, session ->
            val (hour, minute) = when (session.slotName) {
                "Morning Focus" -> 6 to 30
                "Afternoon Concepts" -> 16 to 30
                "Evening Deep Study" -> 18 to 30
                else -> 21 to 30
            }
            StudyAlarmManager.scheduleStudyAlarm(
                context = context,
                hour = hour,
                minute = minute,
                subject = session.subjectName,
                chapter = session.chapterName,
                durationMinutes = session.durationMinutes,
                sessionType = session.slotName,
                requestCode = 1000 + index
            )
        }
    }

    // "Am I Ready for Madhyamik?" analysis engine
    fun calculateReadiness(
        completedCount: Int,
        totalChapters: Int,
        revisionCount: Int,
        testAttempts: List<TestHistoryEntity>,
        streakDays: Int
    ): ReadinessReport {
        val syllabusRatio = if (totalChapters > 0) (completedCount.toFloat() / totalChapters.toFloat()) else 0.4f
        val syllabusPercent = (syllabusRatio * 100).toInt().coerceIn(15, 100)

        val avgMockScore = if (testAttempts.isNotEmpty()) {
            testAttempts.map { it.scorePercentage }.average().toInt()
        } else {
            72
        }

        val revisionScore = (revisionCount * 12 + 40).coerceIn(30, 95)
        val consistencyScore = (streakDays * 8 + 45).coerceIn(40, 98)

        val overallScore = ((syllabusPercent * 0.40) + (revisionScore * 0.25) + (avgMockScore * 0.20) + (consistencyScore * 0.15)).toInt().coerceIn(20, 99)

        val status = when {
            overallScore >= 80 -> "🌟 Exam Ready & High Scoring Zone"
            overallScore >= 60 -> "📈 Strong Progress — Solid First Division Track"
            else -> "⚡ Acceleration Needed — Focus on VVI Chapters"
        }

        val strongSubjects = listOf("Mathematics (গণিত)", "Life Science (জীবনবিজ্ঞান)", "Bengali (বাংলা)")
        val weakSubjects = listOf("Physical Science Numericals (ভৌতবিজ্ঞান অংক)", "History Analytical Questions (ইতিহাস ৪/৮ নম্বর)")

        val urgentNextSteps = listOf(
            "ভৌতবিজ্ঞানের গ্যাসের আচরণ ও চলতড়িতের সূত্রের অংক খাতায় আলাদা করে করো",
            "জীবনবিজ্ঞানের নিউরোন ও চোখের ৫-নম্বর চিহ্নিত চিত্র পেন্সিল দিয়ে ৩ বার অভ্যাস করো",
            "গণিতে উপপাদ্য ৩২ ও ৩৪ না দেখে লিখে সময় মেপে পরীক্ষা দাও",
            "প্রতিদিন অন্তত ১০টি করে অধ্যায়ভিত্তিক MCQ প্র্যাকটিস চালিয়ে যাও"
        )

        val suggestedPlan = "সকাল ১ ঘণ্টা গণিত উপপাদ্য ও বীজগণিত | বিকেল ৪৫ মিনিট ভৌতবিজ্ঞান অংক | সন্ধ্যা ১ ঘণ্টা ইতিহাস ও জীবনবিজ্ঞান | রাতে ৩০ মিনিট স্পেসড রিভিশন।"

        return ReadinessReport(
            overallScore = overallScore,
            syllabusPercent = syllabusPercent,
            revisionScore = revisionScore,
            mockScore = avgMockScore,
            consistencyScore = consistencyScore,
            readinessStatus = status,
            strongSubjects = strongSubjects,
            weakSubjects = weakSubjects,
            urgentNextSteps = urgentNextSteps,
            suggestedWeeklyPlan = suggestedPlan
        )
    }
}
