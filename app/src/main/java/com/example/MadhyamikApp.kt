package com.example

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MadhyamikApp : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_STUDY_ALARM,
                "Madhyamik Study & Revision Alarms",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Timely alerts for your scheduled study sessions, revisions, and mock tests"
                enableVibration(true)
                setShowBadge(true)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_STUDY_ALARM = "madhyamik_study_alarm_channel"
    }
}
