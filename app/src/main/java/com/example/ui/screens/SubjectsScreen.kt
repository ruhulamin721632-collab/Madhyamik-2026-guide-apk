package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.data.model.Chapter
import com.example.data.model.Question
import com.example.data.model.SubjectType
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.AcademicGold
import com.example.ui.theme.SuccessEmerald
import com.example.ui.viewmodel.MadhyamikUiState

@Composable
fun SubjectsScreen(
    uiState: MadhyamikUiState,
    onSelectSubject: (SubjectType?) -> Unit,
    onToggleChapter: (String, String) -> Unit,
    onStartTestForChapter: (String) -> Unit,
    onAskAiAboutChapter: (String) -> Unit
) {
    val selectedSubject = uiState.selectedSubject
    var selectedChapterForDetail by remember { mutableStateOf<Chapter?>(null) }

    if (selectedChapterForDetail != null) {
        ChapterDetailView(
            chapter = selectedChapterForDetail!!,
            questions = uiState.questions.filter { it.chapterId == selectedChapterForDetail!!.id },
            onBack = { selectedChapterForDetail = null },
            onToggleCompletion = { onToggleChapter(selectedChapterForDetail!!.id, selectedChapterForDetail!!.subjectId) },
            onStartPractice = { onStartTestForChapter(selectedChapterForDetail!!.subjectId) },
            onAskAi = { onAskAiAboutChapter(selectedChapterForDetail!!.titleBn) }
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("subjects_screen")
    ) {
        // Top Subject Tabs / Chips
        Surface(
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Text(
                    text = "Class 10 WBBSE Subjects",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Select a subject to explore chapters, notes & questions",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                ScrollableTabRow(
                    selectedTabIndex = if (selectedSubject == null) 0 else uiState.subjects.indexOf(selectedSubject) + 1,
                    edgePadding = 0.dp,
                    divider = {}
                ) {
                    Tab(
                        selected = selectedSubject == null,
                        onClick = { onSelectSubject(null) },
                        text = { Text("All Subjects", fontSize = 13.sp) },
                        modifier = Modifier.testTag("tab_all_subjects")
                    )
                    uiState.subjects.forEachIndexed { index, sub ->
                        Tab(
                            selected = selectedSubject == sub,
                            onClick = { onSelectSubject(sub) },
                            text = { Text(sub.nameBn.split(" ")[0], fontSize = 13.sp) },
                            modifier = Modifier.testTag("tab_subject_${sub.id}")
                        )
                    }
                }
            }
        }

        // Chapters List
        val filteredChapters = if (selectedSubject == null) {
            uiState.chapters
        } else {
            uiState.chapters.filter { it.subjectId == selectedSubject.id }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredChapters) { chapter ->
                val parentSubject = uiState.subjects.find { it.id == chapter.subjectId } ?: SubjectType.BENGALI
                ChapterCard(
                    chapter = chapter,
                    subject = parentSubject,
                    onCardClick = { selectedChapterForDetail = chapter },
                    onToggleComplete = { onToggleChapter(chapter.id, chapter.subjectId) }
                )
            }
        }
    }
}

@Composable
private fun ChapterCard(
    chapter: Chapter,
    subject: SubjectType,
    onCardClick: () -> Unit,
    onToggleComplete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .testTag("chapter_card_${chapter.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(subject.composeColor.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${chapter.chapterNumber}",
                            color = subject.composeColor,
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = chapter.titleBn,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${subject.nameEn} • ${chapter.marksWeightage}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Checkbox(
                    checked = chapter.isCompleted,
                    onCheckedChange = { onToggleComplete() },
                    modifier = Modifier.testTag("checkbox_${chapter.id}")
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = chapter.summary,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Sub-pills: Topics -> Notes -> Questions -> MCQ
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                MiniPill(label = "${chapter.keyTopics.size} Topics")
                MiniPill(label = "Notes")
                MiniPill(label = "Important Q&A")
                MiniPill(label = "MCQ")
            }
        }
    }
}

@Composable
private fun MiniPill(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(text = label, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun ChapterDetailView(
    chapter: Chapter,
    questions: List<Question>,
    onBack: () -> Unit,
    onToggleCompletion: () -> Unit,
    onStartPractice: () -> Unit,
    onAskAi: () -> Unit
) {
    var selectedSection by remember { mutableStateOf(0) } // 0: Overview, 1: Q&A Bank, 2: Notes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("chapter_detail_view")
    ) {
        // App Bar
        Surface(tonalElevation = 3.dp, modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack, modifier = Modifier.testTag("chapter_back_button")) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = chapter.titleBn, fontWeight = FontWeight.Bold, fontSize = 15.sp, maxLines = 1)
                    Text(text = chapter.titleEn, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                IconButton(onClick = onAskAi, modifier = Modifier.testTag("ask_ai_chapter_button")) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = "AI Teacher", tint = AcademicBluePrimary)
                }
            }
        }

        // Action Toolbar
        TabRow(selectedTabIndex = selectedSection) {
            Tab(selected = selectedSection == 0, onClick = { selectedSection = 0 }, text = { Text("Overview & Topics") })
            Tab(selected = selectedSection == 1, onClick = { selectedSection = 1 }, text = { Text("Q&A Bank (${questions.size})") })
            Tab(selected = selectedSection == 2, onClick = { selectedSection = 2 }, text = { Text("High-Yield Notes") })
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            if (selectedSection == 0) {
                // Section 0: Overview & Topics
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(text = "Chapter Summary", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = chapter.summary, fontSize = 13.sp, lineHeight = 18.sp)
                        }
                    }
                }

                item {
                    Text(text = "Key Topics for Madhyamik 2027", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }

                items(chapter.keyTopics) { topic ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CheckCircleOutline, contentDescription = null, tint = AcademicBluePrimary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = topic, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onToggleCompletion,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(if (chapter.isCompleted) "✓ Mark Incomplete" else "Mark Completed")
                        }
                        OutlinedButton(
                            onClick = onStartPractice,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Practice MCQ")
                        }
                    }
                }
            } else if (selectedSection == 1) {
                // Section 1: Questions Bank
                if (questions.isEmpty()) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                            Text("No questions mapped specifically for this chapter yet. Click 'Ask AI Teacher' to generate instant practice questions!", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                        }
                    }
                } else {
                    items(questions) { q ->
                        QuestionDetailCard(question = q)
                    }
                }
            } else {
                // Section 2: High Yield Notes
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "📌 Madhyamik Topper's Notes & Points", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = AcademicBluePrimary)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "• সর্বদা পরীক্ষার খাতায় বিভাগ ও দাগ নম্বর সঠিক রাখবে।\n• সংজ্ঞা লেখার ক্ষেত্রে মূল বৈজ্ঞানিক সূত্র বা পরিভাষা অপরিবর্তিত রাখবে।\n• পাটিগণিতের অংকে ডানপাশে রাফ কাজের জন্য ১.৫ ইঞ্চি কলাম রাখবে।\n• ৫ নম্বরের বড় প্রশ্নে সূচনা ও সমাপ্তি স্পষ্ট রাখবে।",
                                fontSize = 13.sp,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionDetailCard(question: Question) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(AcademicBluePrimary.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${question.marks} MARKS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            color = AcademicBluePrimary
                        )
                    }
                    if (question.isVeryImportant) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(AcademicGold.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(text = "★ V.V.I", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = AcademicGold)
                        }
                    }
                    if (question.isOfficialPYQ) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SuccessEmerald.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(text = question.pyqYear ?: "PYQ", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SuccessEmerald)
                        }
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = question.questionBn,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "আদর্শ উত্তর (WBBSE Marking Scheme Answer):",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = AcademicBluePrimary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    if (question.structuredAnswer.introduction.isNotBlank()) {
                        Text(
                            text = "ভূমিকা: ${question.structuredAnswer.introduction}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    if (question.structuredAnswer.points.isNotEmpty()) {
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
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "উপসংহার: ${question.structuredAnswer.conclusion}",
                            fontSize = 12.sp,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
