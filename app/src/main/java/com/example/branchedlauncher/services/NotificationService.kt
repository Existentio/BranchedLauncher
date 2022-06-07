package com.example.branchedlauncher.services

import android.content.Context
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log


class NotificationService : NotificationListenerService() {

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


    @Override
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
//        val sbm: StatusBarNotification
        // We can read notification while posted.
        for (sbm: StatusBarNotification in this.activeNotifications) {
            val title = sbm.getNotification().extras.getString("android.title");
            val text = sbm.getNotification().extras.getString("android.text");
            val package_name = sbm.packageName;
            Log.v("Notification title is:", title!!);
            Log.v("Notification text is:", text!!);
            Log.v("Notification Package Name is:", package_name);
        }
    }


}