package com.existentio.branchedlauncher.ui.searchscreen

import android.app.Application
import com.existentio.branchedlauncher.data.apps.AppsRepository
import com.existentio.branchedlauncher.model.App
import com.existentio.branchedlauncher.ui.BaseViewModel
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
        if (textQuery.trim() != "")
            for (elem in 0 until apps.size) {
                apps[elem].takeIf { apps[elem].name.lowercase().contains(textQuery) }
                    ?.let { filteredApps.add(it) }
            }
        else filteredApps.clear()
        return filteredApps
    }

}