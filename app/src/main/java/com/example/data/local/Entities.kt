package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_profile")
data class StudentProfileEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "Ruhul Amin",
    val schoolClass: String = "Class 10 (Madhyamik 2027)",
    val board: String = "WBBSE (West Bengal Board)",
    val examYear: Int = 2027,
    val targetScore: Int = 90,
    val studyStreakDays: Int = 5,
    val wakeUpTime: String = "06:00 AM",
    val schoolStartTime: String = "10:30 AM",
    val schoolEndTime: String = "04:00 PM",
    val sleepTime: String = "11:00 PM",
    val dailyStudyHours: Float = 4.0f,
    val lastActiveTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "chapter_progress")
data class ChapterProgressEntity(
    @PrimaryKey val chapterId: String,
    val subjectId: String,
    val isCompleted: Boolean,
    val revisionCount: Int,
    val lastRevisedTimestamp: Long
)

@Entity(tableName = "student_notes")
data class StudentNoteEntity(
    @PrimaryKey val id: String,
    val subjectId: String,
    val chapterTitle: String,
    val title: String,
    val content: String,
    val isBookmarked: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "revision_queue")
data class RevisionQueueEntity(
    @PrimaryKey val id: String,
    val itemType: String, // CHAPTER or QUESTION
    val subjectId: String,
    val title: String,
    val scheduledDate: Long,
    val intervalStage: Int = 1, // 1, 3, 7, 30 days
    val isCompleted: Boolean = false
)

@Entity(tableName = "test_history")
data class TestHistoryEntity(
    @PrimaryKey val id: String,
    val testTitle: String,
    val subjectId: String,
    val totalQuestions: Int,
    val correctCount: Int,
    val scorePercentage: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "question_bookmarks")
data class QuestionBookmarkEntity(
    @PrimaryKey val questionId: String,
    val timestamp: Long = System.currentTimeMillis()
)
