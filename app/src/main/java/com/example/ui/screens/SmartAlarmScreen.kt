package com.example.ui.screens

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.receiver.StudyAlarmManager
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.AcademicGold
import com.example.ui.theme.SuccessEmerald
import com.example.ui.viewmodel.MadhyamikUiState

@Composable
fun SmartAlarmScreen(
    uiState: MadhyamikUiState,
    onScheduleAlarms: () -> Unit,
    onStartFocusTimer: (String, Int) -> Unit,
    onPauseFocusTimer: () -> Unit,
    onResumeFocusTimer: () -> Unit,
    onResetFocusTimer: () -> Unit,
    onUpdateStudyHours: (Float) -> Unit
) {
    val context = LocalContext.current
    val canScheduleExact = StudyAlarmManager.canScheduleExact(context)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("smart_alarm_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Smart Auto Study Alarms",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Personalized alarm system calibrated to school, sleep & free study hours",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Exact Alarm & Notification Permission Banner (Android 12/13/14+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !canScheduleExact) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().testTag("permission_banner"),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AlarmOff, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Exact Alarm Permission Recommended",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "To guarantee study alarms trigger precisely on time while device is sleeping, Android requires explicit alarm scheduling permission.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.85f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                                context.startActivity(intent)
                            },
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text("Enable in System Settings", fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        // Focus Pomodoro Timer Section
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("focus_timer_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "⏱️ Deep Study Focus Timer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = AcademicBluePrimary
                    )
                    Text(
                        text = "Subject: ${uiState.currentFocusSubject}",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val minutes = uiState.focusTimerSecondsLeft / 60
                    val seconds = uiState.focusTimerSecondsLeft % 60

                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = String.format("%02d:%02d", minutes, seconds),
                                fontSize = 34.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = if (uiState.isFocusTimerRunning) "Studying..." else "Paused",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        if (uiState.isFocusTimerRunning) {
                            Button(
                                onClick = onPauseFocusTimer,
                                colors = ButtonDefaults.buttonColors(containerColor = AcademicGold),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Default.Pause, contentDescription = null)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Pause")
                            }
                        } else {
                            Button(
                                onClick = onResumeFocusTimer,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Default.PlayArrow, contentDescription = null)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Start Focus")
                            }
                        }

                        OutlinedButton(
                            onClick = onResetFocusTimer,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Reset")
                        }
                    }
                }
            }
        }

        // Student Schedule Parameters Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "📅 Auto-Scheduler Settings",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "Alarms never ring during school hours or sleep time.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ScheduleItem(label = "Wake-up Time", value = uiState.studentProfile.wakeUpTime, icon = Icons.Default.WbSunny)
                    ScheduleItem(label = "School / Class Time", value = "${uiState.studentProfile.schoolStartTime} — ${uiState.studentProfile.schoolEndTime}", icon = Icons.Default.School)
                    ScheduleItem(label = "Sleep Time", value = uiState.studentProfile.sleepTime, icon = Icons.Default.Bedtime)
                    ScheduleItem(label = "Daily Study Target", value = "${uiState.studentProfile.dailyStudyHours} Hours / day", icon = Icons.Default.Timer)

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onScheduleAlarms,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("activate_alarms_button"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.AlarmAdd, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Sync & Activate All Alarms")
                    }
                }
            }
        }

        // Active Daily Study Alarms
        item {
            Text(
                text = "🔔 Active Study Alarms for Today",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        items(uiState.dailySessions) { session ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(AcademicBluePrimary.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Alarm, contentDescription = null, tint = AcademicBluePrimary, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = session.slotName, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(text = session.timeLabel, fontSize = 11.sp, color = AcademicBluePrimary, fontWeight = FontWeight.SemiBold)
                            Text(text = "${session.subjectName}: ${session.chapterName}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    Switch(
                        checked = true,
                        onCheckedChange = { /* toggle individual */ },
                        modifier = Modifier.height(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ScheduleItem(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = AcademicBluePrimary, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}
