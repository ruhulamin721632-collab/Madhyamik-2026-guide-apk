package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.data.model.Question
import com.example.data.model.SubjectType
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.AcademicGold
import com.example.ui.theme.SuccessEmerald
import com.example.ui.viewmodel.MadhyamikUiState

@Composable
fun QuestionsScreen(
    uiState: MadhyamikUiState,
    onSelectMarksFilter: (Int?) -> Unit,
    onSelectSubject: (SubjectType?) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onToggleBookmark: (String) -> Unit,
    onAskAiAboutQuestion: (String) -> Unit
) {
    val selectedMarks = uiState.selectedMarksFilter
    val selectedSubject = uiState.selectedSubject
    var verifiedOnly by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("questions_screen")
    ) {
        // Search & Filters Header
        Surface(tonalElevation = 2.dp, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Madhyamik Question-Answer Bank",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Structured 1, 2, 3 & 5 mark answers with WBBSE marking patterns",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Search Box
                OutlinedTextField(
                    value = uiState.questionSearchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_questions_input"),
                    placeholder = { Text("Search questions, definitions, formulas...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (uiState.questionSearchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchQueryChange("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Marks Filter Chips
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item {
                        FilterChip(
                            selected = selectedMarks == null,
                            onClick = { onSelectMarksFilter(null) },
                            label = { Text("All Marks") },
                            modifier = Modifier.testTag("chip_marks_all")
                        )
                    }
                    item {
                        FilterChip(
                            selected = selectedMarks == 1,
                            onClick = { onSelectMarksFilter(1) },
                            label = { Text("1 Mark (VSA / MCQ)") },
                            modifier = Modifier.testTag("chip_marks_1")
                        )
                    }
                    item {
                        FilterChip(
                            selected = selectedMarks == 2,
                            onClick = { onSelectMarksFilter(2) },
                            label = { Text("2 Marks (Short)") },
                            modifier = Modifier.testTag("chip_marks_2")
                        )
                    }
                    item {
                        FilterChip(
                            selected = selectedMarks == 3,
                            onClick = { onSelectMarksFilter(3) },
                            label = { Text("3 Marks (Explain)") },
                            modifier = Modifier.testTag("chip_marks_3")
                        )
                    }
                    item {
                        FilterChip(
                            selected = selectedMarks == 5,
                            onClick = { onSelectMarksFilter(5) },
                            label = { Text("5 Marks (Structured Essay)") },
                            modifier = Modifier.testTag("chip_marks_5")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Verified Source Label & Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = SuccessEmerald,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Verified Syllabus Content",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = SuccessEmerald
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Official PYQ Only", fontSize = 11.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Switch(
                            checked = verifiedOnly,
                            onCheckedChange = { verifiedOnly = it },
                            modifier = Modifier.height(24.dp)
                        )
                    }
                }
            }
        }

        // Questions List
        val filteredQuestions = uiState.questions.filter { q ->
            val matchMarks = (selectedMarks == null || q.marks == selectedMarks)
            val matchSubject = (selectedSubject == null || q.subjectId == selectedSubject.id)
            val matchVerified = (!verifiedOnly || q.isOfficialPYQ)
            val matchQuery = if (uiState.questionSearchQuery.isBlank()) true else {
                q.questionBn.contains(uiState.questionSearchQuery, ignoreCase = true) ||
                q.chapterTitle.contains(uiState.questionSearchQuery, ignoreCase = true) ||
                q.structuredAnswer.directAnswer.contains(uiState.questionSearchQuery, ignoreCase = true)
            }
            matchMarks && matchSubject && matchVerified && matchQuery
        }

        if (filteredQuestions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.SearchOff,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No questions match your filter.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Try clearing filters or search for another chapter",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredQuestions) { question ->
                    BankQuestionCard(
                        question = question,
                        isBookmarked = uiState.bookmarkedQuestionIds.contains(question.id),
                        onBookmarkToggle = { onToggleBookmark(question.id) },
                        onAskAi = { onAskAiAboutQuestion(question.questionBn) }
                    )
                }
            }
        }
    }
}

@Composable
private fun BankQuestionCard(
    question: Question,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit,
    onAskAi: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .testTag("bank_question_${question.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Badges row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(AcademicBluePrimary)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "${question.marks} MARK${if (question.marks > 1) "S" else ""}",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                    if (question.isVeryImportant) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(AcademicGold.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(text = "★ High Priority", color = AcademicGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    if (question.isOfficialPYQ) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SuccessEmerald.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(text = question.pyqYear ?: "WBBSE PYQ", color = SuccessEmerald, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBookmarkToggle, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) AcademicGold else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = question.chapterTitle,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = question.questionBn,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            // Expanded Exam Answer Mode
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Exam-Style Answer Format:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = AcademicBluePrimary
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Verified, contentDescription = null, tint = SuccessEmerald, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text("Verified Syllabus", fontSize = 10.sp, color = SuccessEmerald, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (question.structuredAnswer.introduction.isNotBlank()) {
                        Text(
                            text = "১. ভূমিকা (Introduction):",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                        Text(
                            text = question.structuredAnswer.introduction,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }

                    if (question.structuredAnswer.points.isNotEmpty()) {
                        Text(
                            text = "২. মূল বিষয়বস্তু (Main Points & Analysis):",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                        question.structuredAnswer.points.forEach { point ->
                            Text(
                                text = point,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    } else {
                        Text(
                            text = question.structuredAnswer.directAnswer,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    }

                    if (question.structuredAnswer.conclusion.isNotBlank()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "৩. উপসংহার (Conclusion):",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                        Text(
                            text = question.structuredAnswer.conclusion,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = onAskAi,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Explain with AI", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}
