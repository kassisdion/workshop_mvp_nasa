package com.eldorne.workshop.ui.main

import com.eldorne.workshop.ui.base.presenter.BaseView

interface MainActivityView : BaseView {
    fun populateName(name: String)
    fun stopProcess()
}
