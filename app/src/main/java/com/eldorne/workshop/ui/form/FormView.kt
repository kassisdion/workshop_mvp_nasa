package com.eldorne.workshop.ui.form

import com.eldorne.workshop.ui.base.presenter.BaseView

interface FormView : BaseView {
    fun setErrorOnFirstName(error: String)
    fun removeErrorOnFirstName()

    fun setErrorOnLastName(error: String)
    fun removeErrorOnLastName()

    fun stopProcess()
    fun navigateToMainActivity(firstName: String, lastName: String)
}