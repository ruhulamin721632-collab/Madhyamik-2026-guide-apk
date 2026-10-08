package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MadhyamikDao {

    @Query("SELECT * FROM student_profile WHERE id = 1 LIMIT 1")
    fun getStudentProfile(): Flow<StudentProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStudentProfile(profile: StudentProfileEntity)

    @Query("SELECT * FROM chapter_progress")
    fun getAllChapterProgress(): Flow<List<ChapterProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateChapterProgress(progress: ChapterProgressEntity)

    @Query("SELECT * FROM student_notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<StudentNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: StudentNoteEntity)

    @Query("DELETE FROM student_notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: String)

    @Query("SELECT * FROM revision_queue WHERE isCompleted = 0 ORDER BY scheduledDate ASC")
    fun getPendingRevisions(): Flow<List<RevisionQueueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRevision(item: RevisionQueueEntity)

    @Query("UPDATE revision_queue SET isCompleted = 1 WHERE id = :id")
    suspend fun markRevisionCompleted(id: String)

    @Query("SELECT * FROM test_history ORDER BY timestamp DESC LIMIT 20")
    fun getTestHistory(): Flow<List<TestHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestResult(result: TestHistoryEntity)

    @Query("SELECT * FROM question_bookmarks")
    fun getAllBookmarks(): Flow<List<QuestionBookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: QuestionBookmarkEntity)

    @Query("DELETE FROM question_bookmarks WHERE questionId = :questionId")
    suspend fun removeBookmark(questionId: String)
}
