package com.existentio.branchedlauncher.model

data class Notification(
    val id: Int,
    val packageName: String,
    val title: String,
    val message: String,
    val postTime: String
)

