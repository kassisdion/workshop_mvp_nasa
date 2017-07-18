package com.eldorne.workshop.ui.form

import com.eldorne.workshop.ui.base.BaseView

/**
 * Created by eldorne on 11/07/17.
 */

interface FormView: BaseView {
    fun stopProcess()
    fun navigateToMainActivity(firstName: String, lastName: String)
    fun setErrorOnFirstName(error: String)
    fun removeErrorFirstName()

    fun removeErrorLastName()
    fun setErrorOnLastName(error: String)


}