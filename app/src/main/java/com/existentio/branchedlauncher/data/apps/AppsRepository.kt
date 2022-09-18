package com.existentio.branchedlauncher.data.apps

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import com.existentio.branchedlauncher.model.App
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppsRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val packageManager = context.packageManager

    private var allApps = mutableListOf<App>()

    fun provideApps(): MutableList<App> {
        if (allApps.isNotEmpty()) allApps.clear()
        val appIntent = Intent(Intent.ACTION_MAIN, null)

        val ri: List<ResolveInfo> =
            packageManager.queryIntentActivities(appIntent, 0)
        return extractInstalledAppsFromOS(ri)
    }

    private fun extractInstalledAppsFromOS(apps: List<ResolveInfo>): MutableList<App> {
        for (ri in apps) {
            val packageName = ri.activityInfo.packageName
            val icon = ri.activityInfo.loadIcon(packageManager)
            val appName = packageManager.getApplicationLabel(
                packageManager.getApplicationInfo(
                    packageName,
                    0
                )
            ).toString()
            val app = App(packageName, appName, icon)
            allApps.add(app)
        }
        return allApps.distinctBy { it.packageName } as ArrayList
    }


}