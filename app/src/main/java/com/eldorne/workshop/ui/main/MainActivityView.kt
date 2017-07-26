package com.eldorne.workshop.ui.main

import com.eldorne.workshop.ui.base.presenter.BaseView

interface MainActivityView : BaseView {
    fun populateName(name: String)
    fun stopProcess()
    fun showError(message: String?)
    fun showLoading(start: Boolean)
    fun showRefreshing(start: Boolean)
    fun populateSpaceObject(data: List<String>)
    fun clearData()
}
