package com.example

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
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
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.*
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.AcademicGold
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.DashboardViewModel
import com.example.ui.viewmodel.MadhyamikViewModel

enum class NavigationScreen(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    HOME("Home", Icons.Default.Home),
    SUBJECTS("Subjects", Icons.AutoMirrored.Filled.MenuBook),
    QUESTIONS("Questions", Icons.Default.HelpCenter),
    TESTS("Tests", Icons.Default.Quiz),
    AZ_GUIDE("A-Z Guide", Icons.Default.AutoStories),
    ALARMS("Alarms", Icons.Default.Alarm),
    REVISION("Revision", Icons.Default.Psychology),
    NOTES("Notes", Icons.Default.EditNote),
    AI_TEACHER("AI Teacher", Icons.Default.SmartToy),
    PROFILE("Profile", Icons.Default.Person)
}

class MainActivity : ComponentActivity() {

    private val viewModel: MadhyamikViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val initialScreen = when (intent?.getStringExtra("OPEN_SCREEN")) {
            "FOCUS_TIMER" -> NavigationScreen.ALARMS
            "AI_TEACHER" -> NavigationScreen.AI_TEACHER
            else -> NavigationScreen.HOME
        }

        setContent {
            MyApplicationTheme {
                MainAppContainer(
                    viewModel = viewModel,
                    dashboardViewModel = dashboardViewModel,
                    initialScreen = initialScreen
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContainer(
    viewModel: MadhyamikViewModel,
    dashboardViewModel: DashboardViewModel = viewModel(),
    initialScreen: NavigationScreen = NavigationScreen.HOME
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dashboardMetrics by dashboardViewModel.dashboardMetrics.collectAsStateWithLifecycle()
    var currentScreen by remember { mutableStateOf(initialScreen) }

    // Request Android 13+ Notification Permission gracefully
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { _ -> }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val status = ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            if (status != PackageManager.PERMISSION_GRANTED) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    // Handle back button: return to HOME if in secondary screen
    BackHandler(enabled = currentScreen != NavigationScreen.HOME) {
        currentScreen = NavigationScreen.HOME
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth > 600.dp

        Row(modifier = Modifier.fillMaxSize()) {
            // NavigationRail for tablets and wide screens
            if (isWideScreen) {
                NavigationRail(
                    modifier = Modifier.testTag("tablet_navigation_rail"),
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(AcademicBluePrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    listOf(
                        NavigationScreen.HOME,
                        NavigationScreen.SUBJECTS,
                        NavigationScreen.QUESTIONS,
                        NavigationScreen.TESTS,
                        NavigationScreen.AZ_GUIDE,
                        NavigationScreen.ALARMS,
                        NavigationScreen.REVISION,
                        NavigationScreen.PROFILE
                    ).forEach { screen ->
                        NavigationRailItem(
                            selected = currentScreen == screen,
                            onClick = { currentScreen = screen },
                            icon = { Icon(screen.icon, contentDescription = screen.label) },
                            label = { Text(screen.label) },
                            modifier = Modifier.testTag("rail_${screen.name.lowercase()}")
                        )
                    }
                }
            }

            Scaffold(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                topBar = {
                    TopAppBar(
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(AcademicBluePrimary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.School,
                                        contentDescription = null,
                                        tint = AcademicGold,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "StudyMate 2027",
                                        fontWeight = FontWeight.Black,
                                        fontSize = 17.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "WBBSE Madhyamik Guide",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        },
                        actions = {
                            // Quick Action Buttons
                            IconButton(
                                onClick = { currentScreen = NavigationScreen.AZ_GUIDE },
                                modifier = Modifier.testTag("top_az_guide_action")
                            ) {
                                Icon(Icons.Default.AutoStories, contentDescription = "A-Z Guide")
                            }
                            IconButton(
                                onClick = { currentScreen = NavigationScreen.REVISION },
                                modifier = Modifier.testTag("top_revision_action")
                            ) {
                                Icon(Icons.Default.Psychology, contentDescription = "Revision")
                            }
                            IconButton(
                                onClick = { currentScreen = NavigationScreen.NOTES },
                                modifier = Modifier.testTag("top_notes_action")
                            ) {
                                Icon(Icons.Default.EditNote, contentDescription = "Notes")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    )
                },
                bottomBar = {
                    if (!isWideScreen) {
                        NavigationBar(
                            modifier = Modifier.testTag("bottom_navigation_bar"),
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 6.dp
                        ) {
                            listOf(
                                NavigationScreen.HOME,
                                NavigationScreen.SUBJECTS,
                                NavigationScreen.QUESTIONS,
                                NavigationScreen.TESTS,
                                NavigationScreen.PROFILE
                            ).forEach { screen ->
                                NavigationBarItem(
                                    selected = currentScreen == screen,
                                    onClick = { currentScreen = screen },
                                    icon = { Icon(screen.icon, contentDescription = screen.label) },
                                    label = { Text(screen.label, fontSize = 11.sp, fontWeight = if (currentScreen == screen) FontWeight.Bold else FontWeight.Normal) },
                                    modifier = Modifier.testTag("nav_${screen.name.lowercase()}")
                                )
                            }
                        }
                    }
                },
                floatingActionButton = {
                    if (currentScreen != NavigationScreen.AI_TEACHER) {
                        ExtendedFloatingActionButton(
                            onClick = { currentScreen = NavigationScreen.AI_TEACHER },
                            icon = { Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = AcademicGold) },
                            text = { Text("Ask AI Teacher", fontWeight = FontWeight.Bold) },
                            containerColor = AcademicBluePrimary,
                            contentColor = Color.White,
                            modifier = Modifier.testTag("ask_ai_teacher_fab")
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .widthIn(max = 800.dp)
                    ) {
                        when (currentScreen) {
                            NavigationScreen.HOME -> DashboardScreen(
                                uiState = uiState,
                                metrics = dashboardMetrics,
                                onNavigateToSubjects = { currentScreen = NavigationScreen.SUBJECTS },
                                onNavigateToSubjectDetail = { sub ->
                                    viewModel.selectSubject(sub)
                                    currentScreen = NavigationScreen.SUBJECTS
                                },
                                onNavigateToAlarms = { currentScreen = NavigationScreen.ALARMS },
                                onNavigateToQuestions = { currentScreen = NavigationScreen.QUESTIONS },
                                onNavigateToTests = { currentScreen = NavigationScreen.TESTS },
                                onNavigateToAiTeacher = { currentScreen = NavigationScreen.AI_TEACHER },
                                onOpenReadiness = { viewModel.setReadinessModalVisible(true) },
                                onStartFocusTimer = { subject, duration ->
                                    viewModel.startFocusTimer(subject, duration)
                                    currentScreen = NavigationScreen.ALARMS
                                },
                                onMarkSessionCompleted = { sessionId ->
                                    viewModel.markSessionCompleted(sessionId)
                                },
                                onResolveBacklog = {
                                    viewModel.resolveBacklog()
                                }
                            )

                            NavigationScreen.SUBJECTS -> SubjectsScreen(
                                uiState = uiState,
                                onSelectSubject = { viewModel.selectSubject(it) },
                                onToggleChapter = { chId, subId -> viewModel.toggleChapterCompletion(chId, subId) },
                                onStartTestForChapter = { subId ->
                                    viewModel.startMockTest(subId)
                                    currentScreen = NavigationScreen.TESTS
                                },
                                onAskAiAboutChapter = { chTitle ->
                                    viewModel.sendAiTeacherMessage("Explain key concepts and high-yield questions for $chTitle")
                                    currentScreen = NavigationScreen.AI_TEACHER
                                }
                            )

                            NavigationScreen.QUESTIONS -> QuestionsScreen(
                                uiState = uiState,
                                onSelectMarksFilter = { viewModel.selectMarksFilter(it) },
                                onSelectSubject = { viewModel.selectSubject(it) },
                                onSearchQueryChange = { viewModel.updateSearchQuery(it) },
                                onToggleBookmark = { viewModel.toggleBookmark(it) },
                                onAskAiAboutQuestion = { questionText ->
                                    viewModel.sendAiTeacherMessage("Please explain this Madhyamik question step-by-step: $questionText")
                                    currentScreen = NavigationScreen.AI_TEACHER
                                }
                            )

                            NavigationScreen.TESTS -> TestsScreen(
                                uiState = uiState,
                                onStartDaily10 = { viewModel.startMockTest(null, 10) },
                                onStartWeekly50 = { viewModel.startMockTest(null, 20) },
                                onStartSubjectTest = { subId -> viewModel.startMockTest(subId, 10) },
                                onSelectMockOption = { qIdx, optIdx -> viewModel.selectMockOption(qIdx, optIdx) },
                                onToggleMarkForReview = { qIdx -> viewModel.toggleMockMarkForReview(qIdx) },
                                onNavigateQuestion = { qIdx -> viewModel.navigateMockQuestion(qIdx) },
                                onSubmitMockTest = { viewModel.submitMockTest() },
                                onDismissResult = { viewModel.dismissMockResult() }
                            )

                            NavigationScreen.AZ_GUIDE -> AZGuideScreen(
                                guideItems = uiState.azGuide
                            )

                            NavigationScreen.ALARMS -> SmartAlarmScreen(
                                uiState = uiState,
                                onScheduleAlarms = { viewModel.scheduleAlarms() },
                                onStartFocusTimer = { subj, mins -> viewModel.startFocusTimer(subj, mins) },
                                onPauseFocusTimer = { viewModel.pauseFocusTimer() },
                                onResumeFocusTimer = { viewModel.resumeFocusTimer() },
                                onResetFocusTimer = { viewModel.resetFocusTimer() },
                                onUpdateStudyHours = { /* updated */ }
                            )

                            NavigationScreen.REVISION -> RevisionScreen(
                                uiState = uiState,
                                onCompleteRevision = { viewModel.completeRevision(it) },
                                onNextFlashcard = { viewModel.nextFlashcard() },
                                onPrevFlashcard = { viewModel.prevFlashcard() },
                                onFlipFlashcard = { viewModel.flipFlashcard() },
                                onResolveBacklog = { viewModel.resolveBacklog() }
                            )

                            NavigationScreen.NOTES -> NotesScreen(
                                uiState = uiState,
                                onCreateNote = { subId, ch, title, content ->
                                    viewModel.createNote(subId, ch, title, content)
                                },
                                onDeleteNote = { viewModel.deleteNote(it) }
                            )

                            NavigationScreen.AI_TEACHER -> AiTeacherScreen(
                                uiState = uiState,
                                onSendMessage = { viewModel.sendAiTeacherMessage(it) }
                            )

                            NavigationScreen.PROFILE -> ProfileScreen(
                                uiState = uiState,
                                onOpenReadiness = { viewModel.setReadinessModalVisible(true) },
                                onUpdateProfile = { viewModel.updateStudentProfile(it) }
                            )
                        }
                    }
                }
            }
        }
    }

    // Modal Dialog: "Am I Ready for Madhyamik?"
    if (uiState.showReadinessModal && uiState.readinessReport != null) {
        AmIReadyDialog(
            report = uiState.readinessReport!!,
            onDismiss = { viewModel.setReadinessModalVisible(false) }
        )
    }
}
