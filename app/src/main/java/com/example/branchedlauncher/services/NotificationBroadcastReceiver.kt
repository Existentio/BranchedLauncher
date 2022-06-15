package com.example.branchedlauncher.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.branchedlauncher.model.Notification

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val id = intent.getIntExtra("id", 0)
        val packageName = intent.getStringExtra("packageName")!!
        val title = intent.getStringExtra("title")!!
        val message = intent.getStringExtra("message")!!
        val postTime = intent.getStringExtra("postTime")!!

        val notifications = mutableListOf<Notification>()
        notifications.add(Notification(id, packageName, title, message, postTime))

        Log.d(
            "notifList", notifications.size.toString()
                    + "\ntext: ${notifications[0]}"
        )
    }

}