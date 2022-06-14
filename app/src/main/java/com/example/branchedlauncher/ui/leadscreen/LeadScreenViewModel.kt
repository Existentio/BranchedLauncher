package com.example.branchedlauncher.ui.leadscreen

import android.app.Application
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.branchedlauncher.data.apps.AppsRepository
import com.example.branchedlauncher.model.App
import com.example.branchedlauncher.services.NotificationBroadcastReceiver
import com.example.branchedlauncher.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LeadScreenViewModel @Inject constructor(
    application: Application,
    private val appsRepository: AppsRepository
) : BaseViewModel(application) {

    private val maxAppsSize = 6

    fun loadApps(): MutableList<App> {
        val apps = appsRepository.provideApps()
        receiveNotifications()
        val result: MutableList<App> = mutableListOf()

        for (x in 0 until maxAppsSize) {
            Log.d(
                "Apps", "name = ${apps[x].name}\n " +
                        "packageName = ${apps[x].packageName}\n " +
                        "icon = ${apps[x].icon}"
            )
            result.add(apps[x])
        }

        Log.d("Apps.size", apps.size.toString())
        Log.d("result.size", result.size.toString())

        return result
    }

    private fun receiveNotifications() {
        LocalBroadcastManager
            .getInstance(context)
            .registerReceiver(
                NotificationBroadcastReceiver(),
                IntentFilter("OBSERVE_APP_NOTIFICATIONS")
            )
    }

}