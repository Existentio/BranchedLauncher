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

    fun loadApps(): MutableList<App> {
        val apps = appsRepository.provideApps()

        val notif = NotificationService()
        Log.d(
            "Notif", "${notif}  ${notif.getActiveNotifications().size} "
        )

        Log.d("Apps.size", apps.size.toString())
        return apps
    }

    fun loadTestApps() = testApps


}