package com.example.branchedlauncher.data.apps

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.ResolveInfo
import com.example.branchedlauncher.model.App
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppsRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val packageManager = context.packageManager

    private var allApps = mutableListOf<App>()
    private lateinit var applicationInfo: ApplicationInfo

    fun provideApps(): MutableList<App> {
        if (allApps.isNotEmpty()) allApps.clear()
        val appIntent = Intent(Intent.ACTION_MAIN, null)

        val ri: List<ResolveInfo> =
            packageManager.queryIntentActivities(appIntent, 0)
        return extractAppsFromOS(ri)
    }

    private fun extractAppsFromOS(apps: List<ResolveInfo>): MutableList<App> {
        for (ri in apps) {
            val packageName = ri.activityInfo.packageName
            val icon = ri.activityInfo.loadIcon(packageManager)
            applicationInfo = packageManager.getApplicationInfo(packageName, 0)
//            val appName = ri.activityInfo.name
            var appName = ""
            for (x in packageName.length - 1 downTo 0)
                if (packageName[x] != '.') appName += packageName[x] else break
            appName = appName.reversed()
            val app = App(packageName, appName, icon)
            allApps.add(app)
        }
        return allApps.distinctBy { it.packageName } as ArrayList
    }

}