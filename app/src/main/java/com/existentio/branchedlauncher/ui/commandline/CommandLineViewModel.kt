package com.existentio.branchedlauncher.ui.commandline

import android.app.Application
import com.existentio.branchedlauncher.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommandLineViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
    // TODO: Implement the ViewModel
}