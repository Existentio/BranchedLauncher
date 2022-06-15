package com.example.branchedlauncher.model

data class Notification(
    val id: Int,
    val packageName: String,
    val title: String,
    val message: String,
    val postTime: String
)

//todo  format postTime to yyyymmdd hh:mm:ss
