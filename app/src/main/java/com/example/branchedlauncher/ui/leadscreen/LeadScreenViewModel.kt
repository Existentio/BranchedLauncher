package com.example.branchedlauncher.ui.leadscreen

import android.app.Application
import android.util.Log
import com.example.branchedlauncher.data.apps.AppsRepository
import com.example.branchedlauncher.data.apps.testApps
import com.example.branchedlauncher.model.App
import com.example.branchedlauncher.services.NotificationService
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
        val result: MutableList<App> = mutableListOf()

        val notif = NotificationService()
        Log.d(
            "Notif", "${notif}  ${notif.getActiveNotifications().size} "
        )

        Log.d("Apps.size", apps.size.toString())

        for (x in 0 until maxAppsSize) {
            Log.d(
                "Apps", "name = ${apps[x].name}\n " +
                        "packageName = ${apps[x].packageName}\n " +
                        "icon = ${apps[x].icon}"
            )
            result.add(apps[x])
        }
        return result
    }

    fun loadTestApps() = testApps


}