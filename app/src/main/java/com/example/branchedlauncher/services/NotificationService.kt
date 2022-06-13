package com.example.branchedlauncher.services

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

        var ticker = ""
        if (sbn!!.notification.tickerText != null) {
            ticker = sbn.notification.tickerText.toString()
        }
        val extras = sbn.notification.extras
        val title = extras.getString("android.title")
        val text = extras.getCharSequence("android.text")

        val package_name = sbn.packageName
        Log.d("TagNotif.title", title!!)
        Log.d("TagNotif.text", text!! as String)
        Log.d("TagNotif.package_name", package_name)
        Log.d("TagNotif.ticker", ticker)

        val intent = Intent("test")
        intent.putExtra("package", package_name)
        intent.putExtra("title", title)
        intent.putExtra("text", text)
        intent.putExtra("ticker", ticker);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)

    }


}