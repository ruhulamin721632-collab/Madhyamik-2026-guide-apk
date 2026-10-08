package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.StudentProfileEntity
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.AcademicGold
import com.example.ui.theme.SuccessEmerald
import com.example.ui.viewmodel.MadhyamikUiState

@Composable
fun ProfileScreen(
    uiState: MadhyamikUiState,
    onOpenReadiness: () -> Unit,
    onUpdateProfile: (StudentProfileEntity) -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val liveAppUrl = "https://ais-pre-jsj2vqfisnw4bvziyyt2jo-687240491437.asia-east1.run.app"
    val profile = uiState.studentProfile

    var isEditing by remember { mutableStateOf(false) }
    var editedName by remember { mutableStateOf(profile.name) }
    var editedTarget by remember { mutableStateOf(profile.targetScore.toString()) }
    var editedHours by remember { mutableStateOf(profile.dailyStudyHours.toString()) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("profile_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Student Profile & Settings",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Madhyamik 2027 Examination Candidate Record",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Student Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("profile_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(AcademicBluePrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(36.dp))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = profile.name,
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "${profile.schoolClass} • ${profile.board}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(AcademicGold.copy(alpha = 0.2f))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Target: ${profile.targetScore}%+",
                                        color = AcademicGold,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(SuccessEmerald.copy(alpha = 0.15f))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "${profile.studyStreakDays}d Streak 🔥",
                                        color = SuccessEmerald,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(14.dp))

                    ProfileDetailRow(label = "Examination Board", value = profile.board)
                    ProfileDetailRow(label = "Target Examination", value = "Madhyamik ${profile.examYear}")
                    ProfileDetailRow(label = "Daily Available Study", value = "${profile.dailyStudyHours} Hours")
                    ProfileDetailRow(label = "Wake-up Schedule", value = profile.wakeUpTime)
                    ProfileDetailRow(label = "School Timings", value = "${profile.schoolStartTime} - ${profile.schoolEndTime}")
                    ProfileDetailRow(label = "Sleep Time", value = profile.sleepTime)

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedButton(
                        onClick = { isEditing = true },
                        modifier = Modifier.fillMaxWidth().testTag("edit_profile_button"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Edit Target & Study Hours")
                    }
                }
            }
        }

        // "Am I Ready for Madhyamik?" button
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Am I Ready for Madhyamik 2027?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Comprehensive diagnostic of syllabus, mock scores & weak spots",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }
                    Button(onClick = onOpenReadiness, shape = RoundedCornerShape(10.dp)) {
                        Text("Check Now")
                    }
                }
            }
        }

        // 🔗 LIVE APP MANDATORY SECTION
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("live_app_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = null,
                            tint = AcademicBluePrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "🔗 LIVE APP",
                            fontWeight = FontWeight.Black,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Your Madhyamik 2027 preparation app is deployed and accessible via the official live URL:",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = liveAppUrl,
                            modifier = Modifier.padding(12.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AcademicBluePrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Buttons: Open Live App & Copy App Link
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(liveAppUrl))
                                context.startActivity(intent)
                            },
                            modifier = Modifier.weight(1f).testTag("open_live_app_button"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Open Live App", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                clipboardManager.setText(AnnotatedString(liveAppUrl))
                                Toast.makeText(context, "App Link Copied!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.weight(1f).testTag("copy_app_link_button"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("📋 Copy App Link", fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Share App Button
                    FilledTonalButton(
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, "Madhyamik 2027 — StudyMate")
                                putExtra(Intent.EXTRA_TEXT, "Prepare for Class 10 WBBSE with Madhyamik 2027 StudyMate! Access the live app here: $liveAppUrl")
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share StudyMate 2027"))
                        },
                        modifier = Modifier.fillMaxWidth().testTag("share_app_button"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Share App with Classmates")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "📱 Install App: Add to Android home screen or install via APK builder.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }

    if (isEditing) {
        AlertDialog(
            onDismissRequest = { isEditing = false },
            title = { Text("Update Profile", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = editedName,
                        onValueChange = { editedName = it },
                        label = { Text("Candidate Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = editedTarget,
                        onValueChange = { editedTarget = it },
                        label = { Text("Target Percentage (e.g. 90)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = editedHours,
                        onValueChange = { editedHours = it },
                        label = { Text("Daily Study Hours") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val target = editedTarget.toIntOrNull() ?: 90
                        val hours = editedHours.toFloatOrNull() ?: 4.0f
                        onUpdateProfile(
                            profile.copy(
                                name = editedName.ifBlank { "Ruhul Amin" },
                                targetScore = target,
                                dailyStudyHours = hours
                            )
                        )
                        isEditing = false
                    }
                ) {
                    Text("Save Changes")
                }
            },
            dismissButton = {
                TextButton(onClick = { isEditing = false }) { Text("Cancel") }
            }
        )
    }
}

@Composable
private fun ProfileDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}
