package com.example.branchedlauncher.ui.searchscreen

import android.app.Application
import com.example.branchedlauncher.data.apps.AppsRepository
import com.example.branchedlauncher.model.App
import com.example.branchedlauncher.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    application: Application,
    private val appsRepository: AppsRepository
) : BaseViewModel(application) {

    fun loadApps(): MutableList<App> = appsRepository.provideApps()

    fun filterApps(
        apps: MutableList<App>,
        textQuery: CharSequence
    ): MutableList<App> {
        val filteredApps: MutableList<App> = mutableListOf()
        if (textQuery != "")
            for (x in 0 until apps.size) {
                val b = apps[x].takeIf { apps[x].name.contains(textQuery) }
                b?.let { filteredApps.add(it) }
            } else filteredApps.clear()
        return filteredApps
    }

}