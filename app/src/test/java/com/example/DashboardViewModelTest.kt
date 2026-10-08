package com.example

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AppDatabase
import com.example.data.local.ChapterProgressEntity
import com.example.data.local.StudentProfileEntity
import com.example.ui.viewmodel.DashboardViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class DashboardViewModelTest {

    private lateinit var database: AppDatabase
    private lateinit var application: Application
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        application = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        viewModel = DashboardViewModel(application, database.madhyamikDao())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `aggregates Room data to expose streak count, study target, and syllabus progress`() = runBlocking {
        val dao = database.madhyamikDao()

        // 1. Insert student profile with specific streak and study hours
        dao.saveStudentProfile(
            StudentProfileEntity(
                name = "Ruhul Amin",
                studyStreakDays = 7,
                dailyStudyHours = 5.0f
            )
        )

        // 2. Insert completed chapters
        dao.updateChapterProgress(
            ChapterProgressEntity(
                chapterId = "bn_ch1",
                subjectId = "bengali",
                isCompleted = true,
                revisionCount = 2,
                lastRevisedTimestamp = System.currentTimeMillis()
            )
        )
        dao.updateChapterProgress(
            ChapterProgressEntity(
                chapterId = "math_ch1",
                subjectId = "math",
                isCompleted = true,
                revisionCount = 1,
                lastRevisedTimestamp = System.currentTimeMillis()
            )
        )

        // 3. Read aggregated metrics from DashboardViewModel StateFlow
        val metrics = viewModel.dashboardMetrics.first { !it.isLoading }

        assertNotNull(metrics)
        assertEquals(7, metrics.streakCount)
        assertEquals(5.0f, metrics.dailyStudyTargetHours, 0.01f)
        assertTrue(metrics.dailyStudyTargetText.contains("5.0 Hours"))
        assertEquals(2, metrics.completedChaptersCount)
        assertTrue("Syllabus progress percentage should be greater than 0", metrics.syllabusProgressPercentage > 0)
    }

    @Test
    fun `incrementStreak updates Room and reflects in exposed metrics`() = runBlocking {
        val dao = database.madhyamikDao()
        dao.saveStudentProfile(StudentProfileEntity(studyStreakDays = 3))

        viewModel.incrementStreak()

        val updatedProfile = dao.getStudentProfile().first { it != null && it.studyStreakDays == 4 }
        assertEquals(4, updatedProfile?.studyStreakDays)
    }
}
