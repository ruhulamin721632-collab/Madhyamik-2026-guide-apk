package com.example.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.MadhyamikApp
import com.example.R

class StudyAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val subject = intent.getStringExtra(EXTRA_SUBJECT) ?: "Madhyamik 2027"
        val chapter = intent.getStringExtra(EXTRA_CHAPTER) ?: "Scheduled Topic"
        val duration = intent.getIntExtra(EXTRA_DURATION, 45)
        val sessionType = intent.getStringExtra(EXTRA_SESSION_TYPE) ?: "Study Session"

        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("OPEN_SCREEN", "FOCUS_TIMER")
            putExtra("SUBJECT", subject)
            putExtra("CHAPTER", chapter)
            putExtra("DURATION", duration)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationTitle = "📚 Study Time: $subject"
        val notificationText = "$sessionType — $chapter ($duration mins). Stay focused and conquer Madhyamik 2027!"

        val notification = NotificationCompat.Builder(context, MadhyamikApp.CHANNEL_STUDY_ALARM)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setStyle(NotificationCompat.BigTextStyle().bigText(
                "$sessionType: $chapter\nSubject: $subject\nRecommended duration: $duration minutes\n\n\"Consistency today = confidence in the exam!\""
            ))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .addAction(
                android.R.drawable.ic_media_play,
                "Start Focus Timer",
                pendingIntent
            )
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify((System.currentTimeMillis() % 100000).toInt(), notification)
    }

    companion object {
        const val EXTRA_SUBJECT = "extra_subject"
        const val EXTRA_CHAPTER = "extra_chapter"
        const val EXTRA_DURATION = "extra_duration"
        const val EXTRA_SESSION_TYPE = "extra_session_type"
    }
}
