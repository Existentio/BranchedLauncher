package com.example.branchedlauncher.ui.leadscreen

import android.app.Application
import android.util.Log
import android.widget.LinearLayout
import com.example.branchedlauncher.data.apps.AppsRepository
import com.example.branchedlauncher.data.apps.testApps
import com.example.branchedlauncher.services.NotificationService
import com.example.branchedlauncher.ui.BaseViewModel
import com.example.branchedlauncher.ui.animation.AnimationPattern
import com.example.branchedlauncher.ui.animation.ClockwiseViewAnimator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LeadScreenViewModel @Inject constructor(
    application: Application,
    private val appsRepository: AppsRepository
) : BaseViewModel(application) {

    fun loadApps() {
        val apps = appsRepository.provideApps()
        for (x in apps)
            Log.d("Apps", "${x.appName} ${x.packageName} ${x.label}")

        val notif = NotificationService()
        Log.d(
            "Notif", "${notif}  ${notif.getActiveNotifications().size} "
        )

        Log.d("Apps.size", apps.size.toString())
    }

    fun loadTestApps() = testApps


}