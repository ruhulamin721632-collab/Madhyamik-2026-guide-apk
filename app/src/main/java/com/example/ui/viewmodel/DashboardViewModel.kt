package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.ChapterProgressEntity
import com.example.data.local.MadhyamikDao
import com.example.data.local.StudentProfileEntity
import com.example.data.repository.SyllabusData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * State holding aggregated Room metrics for the Main Dashboard.
 */
data class DashboardMetricsUiState(
    val studentName: String = "Ruhul Amin",
    val streakCount: Int = 0,
    val dailyStudyTargetHours: Float = 4.0f,
    val dailyStudyTargetText: String = "4.0 Hours (4 Sessions)",
    val syllabusProgressPercentage: Int = 0,
    val completedChaptersCount: Int = 0,
    val totalChaptersCount: Int = 0,
    val todayRevisionTargetCount: Int = 0,
    val targetScorePercentage: Int = 90,
    val subjectProgressPercentages: Map<String, Int> = emptyMap(),
    val overallReadinessScore: Int = 0,
    val isLoading: Boolean = false
)

/**
 * ViewModel that aggregates data from the Room database to calculate and expose
 * the daily study target, syllabus progress percentage, and streak count for the main dashboard.
 */
class DashboardViewModel @JvmOverloads constructor(
    application: Application,
    private val dao: MadhyamikDao = AppDatabase.getDatabase(application).madhyamikDao()
) : AndroidViewModel(application) {

    private val allSyllabusChapters = SyllabusData.chapters
    private val totalChapters = allSyllabusChapters.size

    val dashboardMetrics: StateFlow<DashboardMetricsUiState> = combine(
        dao.getStudentProfile(),
        dao.getAllChapterProgress(),
        dao.getPendingRevisions()
    ) { profile, chapterProgressList, pendingRevisions ->
        calculateMetrics(profile, chapterProgressList, pendingRevisions.size)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardMetricsUiState(
            totalChaptersCount = totalChapters,
            isLoading = true
        )
    )

    private fun calculateMetrics(
        profile: StudentProfileEntity?,
        progressList: List<ChapterProgressEntity>,
        pendingRevisionsCount: Int
    ): DashboardMetricsUiState {
        val completedCount = progressList.count { it.isCompleted }
        val syllabusPercentage = if (totalChapters > 0) {
            ((completedCount.toFloat() / totalChapters.toFloat()) * 100).toInt().coerceIn(0, 100)
        } else {
            0
        }

        val streak = profile?.studyStreakDays ?: 1
        val dailyHours = profile?.dailyStudyHours ?: 4.0f
        val studentName = profile?.name ?: "Ruhul Amin"
        val targetScore = profile?.targetScore ?: 90

        // Calculate progress percentage per subject
        val subjectMap = mutableMapOf<String, Int>()
        SyllabusData.subjects.forEach { subject ->
            val subChapters = allSyllabusChapters.filter { it.subjectId == subject.id }
            val subCompleted = progressList.count { p ->
                p.isCompleted && subChapters.any { it.id == p.chapterId }
            }
            val pct = if (subChapters.isNotEmpty()) {
                ((subCompleted.toFloat() / subChapters.size.toFloat()) * 100).toInt()
            } else {
                0
            }
            subjectMap[subject.id] = pct
        }

        // Calculate holistic readiness score based on syllabus % + streak + revision
        val totalRevisions = progressList.sumOf { it.revisionCount }
        val revisionWeight = (totalRevisions * 8 + 30).coerceIn(20, 95)
        val streakWeight = (streak * 6 + 40).coerceIn(30, 95)
        val readinessScore = ((syllabusPercentage * 0.50) + (revisionWeight * 0.25) + (streakWeight * 0.25)).toInt().coerceIn(10, 99)

        return DashboardMetricsUiState(
            studentName = studentName,
            streakCount = streak,
            dailyStudyTargetHours = dailyHours,
            dailyStudyTargetText = "${String.format("%.1f", dailyHours)} Hours (4 Daily Sessions)",
            syllabusProgressPercentage = syllabusPercentage,
            completedChaptersCount = completedCount,
            totalChaptersCount = totalChapters,
            todayRevisionTargetCount = pendingRevisionsCount,
            targetScorePercentage = targetScore,
            subjectProgressPercentages = subjectMap,
            overallReadinessScore = readinessScore,
            isLoading = false
        )
    }

    /**
     * Updates the daily study target hours in the Room database.
     */
    fun updateDailyStudyTargetHours(hours: Float) {
        viewModelScope.launch {
            val current = dao.getStudentProfile().firstOrNull() ?: StudentProfileEntity()
            dao.saveStudentProfile(current.copy(dailyStudyHours = hours))
        }
    }

    /**
     * Increments the study streak count in the Room database.
     */
    fun incrementStreak() {
        viewModelScope.launch {
            val current = dao.getStudentProfile().firstOrNull() ?: StudentProfileEntity()
            dao.saveStudentProfile(current.copy(studyStreakDays = current.studyStreakDays + 1))
        }
    }

    /**
     * Updates the completion status of a chapter in Room and recalculates metrics.
     */
    fun setChapterCompleted(chapterId: String, subjectId: String, completed: Boolean) {
        viewModelScope.launch {
            val currentList = dao.getAllChapterProgress().firstOrNull() ?: emptyList()
            val existing = currentList.find { it.chapterId == chapterId }
            val newRevisionCount = if (completed) (existing?.revisionCount ?: 0) + 1 else (existing?.revisionCount ?: 0)

            dao.updateChapterProgress(
                ChapterProgressEntity(
                    chapterId = chapterId,
                    subjectId = subjectId,
                    isCompleted = completed,
                    revisionCount = newRevisionCount,
                    lastRevisedTimestamp = System.currentTimeMillis()
                )
            )
        }
    }
}
