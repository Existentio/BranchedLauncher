package com.example.branchedlauncher.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val id = intent.getStringExtra("id")
        val packageName = intent.getStringExtra("packageName")
        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")

        var notifications = mutableListOf<String>()
        id?.let { notifications.add(it) }
//        text?.let { notifications.add(it) }

        Log.d(
            "notifList", notifications.size.toString()
                    + "\ntext: ${notifications[0]}"
                    + "\nid : $id"
        )
    }
}