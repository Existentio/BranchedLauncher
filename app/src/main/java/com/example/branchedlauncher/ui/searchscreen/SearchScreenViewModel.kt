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
        queryText: CharSequence
    ): List<App> {
        val filteredApps: MutableList<App> = mutableListOf()
        for (x in apps) {
            x.name
                .filter { it.toString().startsWith(queryText, ignoreCase = true) }

            filteredApps.add(x)
        }


        return filteredApps
    }

}