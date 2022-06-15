package com.example.branchedlauncher.services

import android.app.Notification.EXTRA_TEXT
import android.app.Notification.EXTRA_TITLE
import android.content.Context
import android.content.Intent
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class NotificationService : NotificationListenerService() {

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)

        val extras = sbn.notification?.extras
        val id = sbn.id
        val packageName = sbn.packageName
        val postTime = sbn.postTime.toString()
        val title = extras?.getString(EXTRA_TITLE)
        val message = extras?.getCharSequence(EXTRA_TEXT)

        val intent = Intent("OBSERVE_APP_NOTIFICATIONS")
        intent.putExtra("id", id)
        intent.putExtra("packageName", packageName)
        intent.putExtra("title", title)
        intent.putExtra("message", message)
        intent.putExtra("postTime", postTime)

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)

    }


}