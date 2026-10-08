package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MockTestResult
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.AcademicGold
import com.example.ui.theme.SuccessEmerald
import com.example.ui.viewmodel.MadhyamikUiState

@Composable
fun TestsScreen(
    uiState: MadhyamikUiState,
    onStartDaily10: () -> Unit,
    onStartWeekly50: () -> Unit,
    onStartSubjectTest: (String) -> Unit,
    onSelectMockOption: (Int, Int) -> Unit,
    onToggleMarkForReview: (Int) -> Unit,
    onNavigateQuestion: (Int) -> Unit,
    onSubmitMockTest: () -> Unit,
    onDismissResult: () -> Unit
) {
    if (uiState.isMockTestActive) {
        MockTestActiveView(
            uiState = uiState,
            onSelectOption = onSelectMockOption,
            onToggleReview = onToggleMarkForReview,
            onNavigate = onNavigateQuestion,
            onSubmit = onSubmitMockTest
        )
        return
    }

    if (uiState.lastMockResult != null) {
        MockTestResultView(
            result = uiState.lastMockResult,
            onDismiss = onDismissResult,
            onRetake = onStartDaily10
        )
        return
    }

    // Default Tests Selection Dashboard
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("tests_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Madhyamik 2027 Mock Tests & MCQs",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Strict WBBSE pattern timer tests, automatic scoring & weak-chapter detection",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Daily 10 Challenge Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStartDaily10() }
                    .testTag("start_daily_10_card"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(AcademicBluePrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Bolt, contentDescription = null, tint = AcademicGold, modifier = Modifier.size(28.dp))
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Daily 10 MCQ Sprint",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "10 Mixed syllabus questions • 10 minutes timer • Instant score analysis",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                    Button(onClick = onStartDaily10, modifier = Modifier.testTag("daily_10_button")) {
                        Text("Start")
                    }
                }
            }
        }

        // Weekly 50 MCQ Challenge
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStartWeekly50() }
                    .testTag("start_weekly_50_card"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(AcademicGold),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.MilitaryTech, contentDescription = null, tint = Color.Black, modifier = Modifier.size(28.dp))
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Weekly 50 MCQ Mega Test",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Comprehensive multi-subject mock with rank estimation",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }
                    Button(onClick = onStartWeekly50, modifier = Modifier.testTag("weekly_50_button")) {
                        Text("Start")
                    }
                }
            }
        }

        // Subject-Wise Tests
        item {
            Text(
                text = "Subject-Wise MCQ Mock Tests",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        items(uiState.subjects) { subject ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStartSubjectTest(subject.id) }
                    .testTag("subject_test_${subject.id}"),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(subject.composeColor.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Quiz, contentDescription = null, tint = subject.composeColor, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = subject.nameBn, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(text = "${subject.nameEn} • Chapter-wise MCQs", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    OutlinedButton(
                        onClick = { onStartSubjectTest(subject.id) },
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Take Test", fontSize = 12.sp)
                    }
                }
            }
        }

        // Test History & Performance
        if (uiState.testHistory.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Recent Test Attempts",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            items(uiState.testHistory.take(5)) { attempt ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = attempt.testTitle, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(text = "Score: ${attempt.correctCount} / ${attempt.totalQuestions} Questions", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (attempt.scorePercentage >= 70) SuccessEmerald else AcademicGold)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "${attempt.scorePercentage}%",
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MockTestActiveView(
    uiState: MadhyamikUiState,
    onSelectOption: (Int, Int) -> Unit,
    onToggleReview: (Int) -> Unit,
    onNavigate: (Int) -> Unit,
    onSubmit: () -> Unit
) {
    val questions = uiState.activeMockTestQuestions
    val currentIndex = uiState.currentMockQuestionIndex
    val currentQuestion = questions.getOrNull(currentIndex) ?: return

    val mins = uiState.mockTimeRemainingSeconds / 60
    val secs = uiState.mockTimeRemainingSeconds % 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("mock_test_active_view")
    ) {
        // Test Top App Bar with Timer
        Surface(tonalElevation = 3.dp, modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Madhyamik Mock Test", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(text = "Question ${currentIndex + 1} of ${questions.size}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Timer,
                        contentDescription = "Timer",
                        tint = if (mins < 2) MaterialTheme.colorScheme.error else AcademicBluePrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format("%02d:%02d", mins, secs),
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp,
                        color = if (mins < 2) MaterialTheme.colorScheme.error else AcademicBluePrimary
                    )
                }

                Button(
                    onClick = onSubmit,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.testTag("submit_test_button")
                ) {
                    Text("Submit Test", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Question Palette Selector
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(questions) { idx, q ->
                val isAnswered = q.selectedOptionIndex != -1
                val isSelected = idx == currentIndex

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                isSelected -> AcademicBluePrimary
                                q.isMarkedForReview -> AcademicGold
                                isAnswered -> SuccessEmerald
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            }
                        )
                        .clickable { onNavigate(idx) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${idx + 1}",
                        color = if (isSelected || isAnswered || q.isMarkedForReview) Color.White else MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }

        Divider()

        // Current Question Card & Options
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentQuestion.question.chapterTitle,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AcademicBluePrimary
                )
                TextButton(onClick = { onToggleReview(currentIndex) }) {
                    Icon(
                        imageVector = if (currentQuestion.isMarkedForReview) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = AcademicGold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (currentQuestion.isMarkedForReview) "Marked" else "Mark for Review", fontSize = 11.sp, color = AcademicGold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = currentQuestion.question.questionBn,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // MCQ Options
            currentQuestion.question.mcqOptions.forEachIndexed { optIndex, optionText ->
                val isOptionSelected = currentQuestion.selectedOptionIndex == optIndex
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onSelectOption(currentIndex, optIndex) }
                        .testTag("option_${currentIndex}_$optIndex"),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isOptionSelected) AcademicBluePrimary.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface
                    ),
                    border = CardDefaults.outlinedCardBorder(enabled = isOptionSelected)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isOptionSelected,
                            onClick = { onSelectOption(currentIndex, optIndex) }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = optionText,
                            fontSize = 14.sp,
                            fontWeight = if (isOptionSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        // Bottom Navigation Bar
        Surface(tonalElevation = 2.dp, modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { if (currentIndex > 0) onNavigate(currentIndex - 1) },
                    enabled = currentIndex > 0
                ) {
                    Icon(Icons.Default.ChevronLeft, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Previous")
                }

                if (currentIndex < questions.size - 1) {
                    Button(onClick = { onNavigate(currentIndex + 1) }) {
                        Text("Next")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.ChevronRight, contentDescription = null)
                    }
                } else {
                    Button(
                        onClick = onSubmit,
                        colors = ButtonDefaults.buttonColors(containerColor = SuccessEmerald)
                    ) {
                        Text("Submit Final Test")
                    }
                }
            }
        }
    }
}

@Composable
fun MockTestResultView(
    result: MockTestResult,
    onDismiss: () -> Unit,
    onRetake: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("mock_test_result_view"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(if (result.scorePercentage >= 70) SuccessEmerald.copy(alpha = 0.2f) else AcademicGold.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (result.scorePercentage >= 70) Icons.Default.CheckCircle else Icons.Default.Refresh,
                    contentDescription = null,
                    tint = if (result.scorePercentage >= 70) SuccessEmerald else AcademicGold,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Mock Test Completed!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = result.testTitle,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Score Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${result.scorePercentage}%",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Score: ${result.correctCount} / ${result.totalQuestions} Marks",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        // Stats grid
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ResultStatCard(label = "Correct", count = result.correctCount, color = SuccessEmerald, modifier = Modifier.weight(1f))
                ResultStatCard(label = "Wrong", count = result.wrongCount, color = MaterialTheme.colorScheme.error, modifier = Modifier.weight(1f))
                ResultStatCard(label = "Skipped", count = result.skippedCount, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
            }
        }

        // Weak Chapters Analysis
        if (result.weakChapters.isNotEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Identified Weak Chapters:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        result.weakChapters.forEach { ch ->
                            Text(text = "• $ch", fontSize = 12.sp, color = MaterialTheme.colorScheme.onErrorContainer)
                        }
                    }
                }
            }
        }

        // Action Buttons
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onRetake,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Retake Test")
                }
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Back to Dashboard")
                }
            }
        }
    }
}

@Composable
private fun ResultStatCard(label: String, count: Int, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$count", fontSize = 20.sp, fontWeight = FontWeight.Black, color = color)
            Text(text = label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
