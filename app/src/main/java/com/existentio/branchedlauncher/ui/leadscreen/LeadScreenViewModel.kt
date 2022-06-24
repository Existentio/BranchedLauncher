package com.existentio.branchedlauncher.ui.leadscreen

import android.app.Application
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.existentio.branchedlauncher.data.apps.AppsRepository
import com.existentio.branchedlauncher.model.App
import com.existentio.branchedlauncher.services.NotificationBroadcastReceiver
import com.existentio.branchedlauncher.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val MAX_APPS_SIZE = 6

@HiltViewModel
class LeadScreenViewModel @Inject constructor(
    application: Application,
    private val appsRepository: AppsRepository
) : BaseViewModel(application) {

    fun loadApps(): MutableList<App> {
        val apps = appsRepository.provideApps()
        val result: MutableList<App> = mutableListOf()

//        receiveNotifications()

        for (x in 0 until MAX_APPS_SIZE) {
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

    fun loadRandomApps(): MutableList<App> {
        val apps = loadApps().shuffled()
        val result: MutableList<App> = mutableListOf()
        for (x in 0 until MAX_APPS_SIZE)
            result.add(apps[x])
        return result
    }

    fun loadFavoritedApps(): MutableList<App> {
        return mutableListOf()
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