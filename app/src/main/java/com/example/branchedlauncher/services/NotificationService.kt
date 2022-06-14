package com.example.branchedlauncher.services

import android.app.Notification.EXTRA_TEXT
import android.app.Notification.EXTRA_TITLE
import android.content.Context
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class NotificationService : NotificationListenerService() {

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    @Override
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.d("NotificationService", "onNotificationPosted()")


        val extras = sbn?.notification?.extras
        val id = sbn?.id.toString()
        val packageName = sbn?.packageName
        val title = extras?.getString(EXTRA_TITLE)
        val text = extras?.getCharSequence(EXTRA_TEXT)

        Log.d("TagNotif.title", title!!)
        Log.d("TagNotif.text", text!! as String)
        Log.d("TagNotif.package_name", packageName!!)

        val intent = Intent("OBSERVE_APP_NOTIFICATIONS")
        intent.putExtra("id", id)
        intent.putExtra("packageName", packageName)
        intent.putExtra("title", title)
        intent.putExtra("text", text)

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)

    }


}