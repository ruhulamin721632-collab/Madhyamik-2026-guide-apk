package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.*
import com.example.data.model.*
import com.example.data.repository.MadhyamikRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit

data class MadhyamikUiState(
    val studentProfile: StudentProfileEntity = StudentProfileEntity(),
    val subjects: List<SubjectType> = emptyList(),
    val chapters: List<Chapter> = emptyList(),
    val questions: List<Question> = emptyList(),
    val selectedSubject: SubjectType? = null,
    val selectedMarksFilter: Int? = null,
    val selectedChapter: Chapter? = null,
    val questionSearchQuery: String = "",
    val verifiedOnlyFilter: Boolean = false,
    val dailySessions: List<StudySession> = emptyList(),
    val backlogCount: Int = 1,
    val pendingRevisions: List<RevisionQueueEntity> = emptyList(),
    val azGuide: List<AZGuideItem> = emptyList(),
    val flashcards: List<Flashcard> = emptyList(),
    val currentFlashcardIndex: Int = 0,
    val isFlashcardFlipped: Boolean = false,
    val studentNotes: List<StudentNoteEntity> = emptyList(),
    val bookmarkedQuestionIds: Set<String> = emptySet(),
    val testHistory: List<TestHistoryEntity> = emptyList(),
    // Countdown
    val countdownDays: Long = 124,
    val countdownHours: Long = 14,
    val countdownMinutes: Long = 30,
    // Readiness
    val readinessReport: ReadinessReport? = null,
    val showReadinessModal: Boolean = false,
    // AI Teacher
    val chatMessages: List<ChatMessage> = emptyList(),
    val isAiThinking: Boolean = false,
    // Focus Timer
    val focusTimerSecondsLeft: Int = 25 * 60,
    val focusTimerTotalSeconds: Int = 25 * 60,
    val isFocusTimerRunning: Boolean = false,
    val currentFocusSubject: String = "Mathematics",
    // Mock Test Simulator
    val activeMockTestQuestions: List<MockTestQuestion> = emptyList(),
    val currentMockQuestionIndex: Int = 0,
    val isMockTestActive: Boolean = false,
    val mockTimeRemainingSeconds: Int = 30 * 60,
    val lastMockResult: MockTestResult? = null,
    val alarmsEnabled: Boolean = true
)

class MadhyamikViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MadhyamikRepository

    private val _uiState = MutableStateFlow(MadhyamikUiState())
    val uiState: StateFlow<MadhyamikUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var mockTestTimerJob: Job? = null

    init {
        val database = AppDatabase.getDatabase(application)
        repository = MadhyamikRepository(application, database.madhyamikDao())

        val initialSubjects = repository.getSubjects()
        val initialChapters = repository.getChapters()
        val initialQuestions = repository.getQuestions()
        val initialAZGuide = repository.getAZGuide()
        val initialFlashcards = repository.getFlashcards()

        _uiState.update {
            it.copy(
                subjects = initialSubjects,
                chapters = initialChapters,
                questions = initialQuestions,
                azGuide = initialAZGuide,
                flashcards = initialFlashcards,
                dailySessions = repository.generateDailyStudyPlan(it.studentProfile),
                chatMessages = listOf(
                    ChatMessage(
                        id = "welcome_1",
                        isUser = false,
                        text = "নমস্কার! আমি তোমার 'StudyMate AI Teacher'। ২০২৭ সালের মাধ্যমিক পরীক্ষায় শ্রেষ্ঠ ফল করার জন্য আমি সবসময় তোমার পাশে আছি।\n\nযে-কোনো অংকের সমাধান, বিজ্ঞানের চিত্র, ৫-নম্বরের আদর্শ উত্তর বা দুর্বল টপিক বোঝার জন্য নিচে প্রশ্ন জিজ্ঞাসা করো!",
                        suggestedActions = listOf(
                            "📐 Solve step-by-step math",
                            "📝 5-mark structured answer",
                            "🗣️ Easy language mein samjhao",
                            "⭐ Important questions for 2027",
                            "❓ Quick 5-question Quiz lo"
                        )
                    )
                )
            )
        }

        observeDatabase()
        calculateCountdown()
        refreshReadiness()
    }

    private fun observeDatabase() {
        viewModelScope.launch {
            repository.getStudentProfile().collect { profile ->
                if (profile != null) {
                    _uiState.update { it.copy(studentProfile = profile) }
                } else {
                    repository.saveStudentProfile(StudentProfileEntity())
                }
            }
        }

        viewModelScope.launch {
            repository.getChapterProgress().collect { progressList ->
                val updatedChapters = _uiState.value.chapters.map { ch ->
                    val p = progressList.find { it.chapterId == ch.id }
                    if (p != null) {
                        ch.copy(isCompleted = p.isCompleted, revisionCount = p.revisionCount)
                    } else {
                        ch
                    }
                }
                _uiState.update { it.copy(chapters = updatedChapters) }
                refreshReadiness()
            }
        }

        viewModelScope.launch {
            repository.getPendingRevisions().collect { revisions ->
                _uiState.update { it.copy(pendingRevisions = revisions) }
            }
        }

        viewModelScope.launch {
            repository.getAllNotes().collect { notes ->
                _uiState.update { it.copy(studentNotes = notes) }
            }
        }

        viewModelScope.launch {
            repository.getBookmarks().collect { bookmarks ->
                val ids = bookmarks.map { it.questionId }.toSet()
                _uiState.update { state ->
                    val updatedQuestions = state.questions.map { q ->
                        q.copy(isBookmarked = ids.contains(q.id))
                    }
                    state.copy(bookmarkedQuestionIds = ids, questions = updatedQuestions)
                }
            }
        }

        viewModelScope.launch {
            repository.getTestHistory().collect { history ->
                _uiState.update { it.copy(testHistory = history) }
                refreshReadiness()
            }
        }
    }

    private fun calculateCountdown() {
        // Madhyamik 2027 is scheduled for mid-February 2027 (e.g. Feb 8, 2027)
        val examCalendar = Calendar.getInstance().apply {
            set(2027, Calendar.FEBRUARY, 8, 10, 0, 0)
        }
        val diffMillis = examCalendar.timeInMillis - System.currentTimeMillis()
        if (diffMillis > 0) {
            val days = TimeUnit.MILLISECONDS.toDays(diffMillis)
            val hours = TimeUnit.MILLISECONDS.toHours(diffMillis) % 24
            val minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis) % 60
            _uiState.update {
                it.copy(
                    countdownDays = days,
                    countdownHours = hours,
                    countdownMinutes = minutes
                )
            }
        }
    }

    fun selectSubject(subject: SubjectType?) {
        _uiState.update { it.copy(selectedSubject = subject) }
    }

    fun selectMarksFilter(marks: Int?) {
        _uiState.update { it.copy(selectedMarksFilter = marks) }
    }

    fun selectChapter(chapter: Chapter?) {
        _uiState.update { it.copy(selectedChapter = chapter) }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(questionSearchQuery = query) }
    }

    fun toggleVerifiedOnly(verifiedOnly: Boolean) {
        _uiState.update { it.copy(verifiedOnlyFilter = verifiedOnly) }
    }

    fun toggleChapterCompletion(chapterId: String, subjectId: String) {
        viewModelScope.launch {
            val current = _uiState.value.chapters.find { it.id == chapterId }?.isCompleted ?: false
            repository.markChapterCompleted(chapterId, subjectId, !current)
        }
    }

    fun toggleBookmark(questionId: String) {
        viewModelScope.launch {
            val isBookmarked = _uiState.value.bookmarkedQuestionIds.contains(questionId)
            repository.toggleBookmark(questionId, !isBookmarked)
        }
    }

    fun completeRevision(revisionId: String) {
        viewModelScope.launch {
            repository.completeRevision(revisionId)
        }
    }

    fun markSessionCompleted(sessionId: String) {
        _uiState.update { state ->
            val updated = state.dailySessions.map { s ->
                if (s.id == sessionId) s.copy(isCompleted = true, isMissed = false) else s
            }
            state.copy(dailySessions = updated)
        }
    }

    fun resolveBacklog() {
        _uiState.update { state ->
            state.copy(backlogCount = 0)
        }
    }

    fun scheduleAlarms() {
        repository.scheduleAllDailyAlarms(_uiState.value.dailySessions)
        _uiState.update { it.copy(alarmsEnabled = true) }
    }

    // AI Teacher
    fun sendAiTeacherMessage(prompt: String) {
        if (prompt.isBlank()) return

        val userMessage = ChatMessage(
            id = "msg_${System.currentTimeMillis()}",
            isUser = true,
            text = prompt
        )

        _uiState.update {
            it.copy(
                chatMessages = it.chatMessages + userMessage,
                isAiThinking = true
            )
        }

        viewModelScope.launch {
            val subjectName = _uiState.value.selectedSubject?.nameBn ?: ""
            val answer = repository.queryAiTeacher(prompt, subjectName)

            val aiMessage = ChatMessage(
                id = "ai_${System.currentTimeMillis()}",
                isUser = false,
                text = answer,
                suggestedActions = listOf(
                    "📝 Give 5-mark answer",
                    "📐 Solve next step",
                    "⭐ VVI Question for 2027"
                )
            )

            _uiState.update {
                it.copy(
                    chatMessages = it.chatMessages + aiMessage,
                    isAiThinking = false
                )
            }
        }
    }

    // Flashcards
    fun nextFlashcard() {
        val total = _uiState.value.flashcards.size
        if (total == 0) return
        _uiState.update {
            it.copy(
                currentFlashcardIndex = (it.currentFlashcardIndex + 1) % total,
                isFlashcardFlipped = false
            )
        }
    }

    fun prevFlashcard() {
        val total = _uiState.value.flashcards.size
        if (total == 0) return
        _uiState.update {
            val prev = if (it.currentFlashcardIndex - 1 < 0) total - 1 else it.currentFlashcardIndex - 1
            it.copy(
                currentFlashcardIndex = prev,
                isFlashcardFlipped = false
            )
        }
    }

    fun flipFlashcard() {
        _uiState.update { it.copy(isFlashcardFlipped = !it.isFlashcardFlipped) }
    }

    // Notes
    fun createNote(subjectId: String, chapterTitle: String, title: String, content: String) {
        viewModelScope.launch {
            val newNote = StudentNoteEntity(
                id = "note_${System.currentTimeMillis()}",
                subjectId = subjectId,
                chapterTitle = chapterTitle,
                title = title,
                content = content
            )
            repository.addNote(newNote)
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    // Profile
    fun updateStudentProfile(profile: StudentProfileEntity) {
        viewModelScope.launch {
            repository.saveStudentProfile(profile)
        }
    }

    // Focus Timer
    fun startFocusTimer(subjectName: String = "Mathematics", minutes: Int = 25) {
        timerJob?.cancel()
        _uiState.update {
            it.copy(
                currentFocusSubject = subjectName,
                focusTimerTotalSeconds = minutes * 60,
                focusTimerSecondsLeft = minutes * 60,
                isFocusTimerRunning = true
            )
        }
        timerJob = viewModelScope.launch {
            while (_uiState.value.focusTimerSecondsLeft > 0 && _uiState.value.isFocusTimerRunning) {
                delay(1000L)
                _uiState.update { it.copy(focusTimerSecondsLeft = it.focusTimerSecondsLeft - 1) }
            }
            if (_uiState.value.focusTimerSecondsLeft <= 0) {
                _uiState.update { it.copy(isFocusTimerRunning = false) }
            }
        }
    }

    fun pauseFocusTimer() {
        _uiState.update { it.copy(isFocusTimerRunning = false) }
        timerJob?.cancel()
    }

    fun resumeFocusTimer() {
        if (_uiState.value.focusTimerSecondsLeft <= 0) return
        _uiState.update { it.copy(isFocusTimerRunning = true) }
        timerJob = viewModelScope.launch {
            while (_uiState.value.focusTimerSecondsLeft > 0 && _uiState.value.isFocusTimerRunning) {
                delay(1000L)
                _uiState.update { it.copy(focusTimerSecondsLeft = it.focusTimerSecondsLeft - 1) }
            }
            if (_uiState.value.focusTimerSecondsLeft <= 0) {
                _uiState.update { it.copy(isFocusTimerRunning = false) }
            }
        }
    }

    fun resetFocusTimer() {
        timerJob?.cancel()
        _uiState.update {
            it.copy(
                isFocusTimerRunning = false,
                focusTimerSecondsLeft = it.focusTimerTotalSeconds
            )
        }
    }

    // Mock Test Simulator
    fun startMockTest(subjectId: String? = null, totalQuestions: Int = 10) {
        val filtered = repository.getQuestions(subjectId = subjectId)
            .filter { it.mcqOptions.isNotEmpty() }
            .shuffled()
            .take(totalQuestions)

        val testQuestions = filtered.map { q ->
            MockTestQuestion(
                id = "mock_q_${q.id}",
                question = q
            )
        }

        mockTestTimerJob?.cancel()
        _uiState.update {
            it.copy(
                activeMockTestQuestions = testQuestions,
                currentMockQuestionIndex = 0,
                isMockTestActive = true,
                mockTimeRemainingSeconds = totalQuestions * 60,
                lastMockResult = null
            )
        }

        mockTestTimerJob = viewModelScope.launch {
            while (_uiState.value.mockTimeRemainingSeconds > 0 && _uiState.value.isMockTestActive) {
                delay(1000L)
                _uiState.update { it.copy(mockTimeRemainingSeconds = it.mockTimeRemainingSeconds - 1) }
            }
            if (_uiState.value.isMockTestActive) {
                submitMockTest()
            }
        }
    }

    fun selectMockOption(questionIndex: Int, optionIndex: Int) {
        _uiState.update { state ->
            val updated = state.activeMockTestQuestions.toMutableList()
            if (questionIndex in updated.indices) {
                updated[questionIndex] = updated[questionIndex].copy(selectedOptionIndex = optionIndex)
            }
            state.copy(activeMockTestQuestions = updated)
        }
    }

    fun toggleMockMarkForReview(questionIndex: Int) {
        _uiState.update { state ->
            val updated = state.activeMockTestQuestions.toMutableList()
            if (questionIndex in updated.indices) {
                val current = updated[questionIndex].isMarkedForReview
                updated[questionIndex] = updated[questionIndex].copy(isMarkedForReview = !current)
            }
            state.copy(activeMockTestQuestions = updated)
        }
    }

    fun navigateMockQuestion(index: Int) {
        if (index in _uiState.value.activeMockTestQuestions.indices) {
            _uiState.update { it.copy(currentMockQuestionIndex = index) }
        }
    }

    fun submitMockTest() {
        mockTestTimerJob?.cancel()
        val questions = _uiState.value.activeMockTestQuestions
        var correct = 0
        var wrong = 0
        var skipped = 0
        val weakChapters = mutableListOf<String>()

        questions.forEach { mq ->
            if (mq.selectedOptionIndex == -1) {
                skipped++
            } else if (mq.selectedOptionIndex == mq.question.correctMcqIndex) {
                correct++
            } else {
                wrong++
                weakChapters.add(mq.question.chapterTitle)
            }
        }

        val total = questions.size
        val scorePercent = if (total > 0) (correct * 100) / total else 0

        val result = MockTestResult(
            testTitle = "Madhyamik 2027 Mock Practice",
            subjectId = questions.firstOrNull()?.question?.subjectId ?: "all",
            totalQuestions = total,
            correctCount = correct,
            wrongCount = wrong,
            skippedCount = skipped,
            scorePercentage = scorePercent,
            timeTakenSeconds = 600L,
            weakChapters = weakChapters.distinct()
        )

        _uiState.update {
            it.copy(
                isMockTestActive = false,
                lastMockResult = result
            )
        }

        viewModelScope.launch {
            repository.recordTestResult(
                TestHistoryEntity(
                    id = "test_${System.currentTimeMillis()}",
                    testTitle = result.testTitle,
                    subjectId = result.subjectId,
                    totalQuestions = total,
                    correctCount = correct,
                    scorePercentage = scorePercent
                )
            )
            refreshReadiness()
        }
    }

    fun dismissMockResult() {
        _uiState.update { it.copy(lastMockResult = null) }
    }

    // Readiness Analysis
    fun refreshReadiness() {
        val completed = _uiState.value.chapters.count { it.isCompleted }
        val total = _uiState.value.chapters.size
        val revisions = _uiState.value.chapters.sumOf { it.revisionCount }
        val attempts = _uiState.value.testHistory
        val streak = _uiState.value.studentProfile.studyStreakDays

        val report = repository.calculateReadiness(
            completedCount = completed,
            totalChapters = total,
            revisionCount = revisions,
            testAttempts = attempts,
            streakDays = streak
        )

        _uiState.update { it.copy(readinessReport = report) }
    }

    fun setReadinessModalVisible(visible: Boolean) {
        if (visible) refreshReadiness()
        _uiState.update { it.copy(showReadinessModal = visible) }
    }
}
