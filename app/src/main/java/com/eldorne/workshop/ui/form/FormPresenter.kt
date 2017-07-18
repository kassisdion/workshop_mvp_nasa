package com.eldorne.workshop.ui.form

import com.eldorne.workshop.ui.MainActivity
import com.eldorne.workshop.ui.base.BasePresenter

/**
 * Created by eldorne on 11/07/17.
 */

class FormPresenter: BasePresenter<FormView>() {

    fun onItemHomeClick() {
        view?.stopProcess()
    }

    fun  onAttemptValidation(firstName: String, lastName: String) {
        if (isInputValid(firstName, lastName)) {
            //Input is valid, start the main activity
            view?.navigateToMainActivity(firstName, lastName)

        }
    }

    private fun isInputValid(firstName: String?,
                             lastName: String): Boolean {
        return (validateFirstName(firstName) && validateLastName(lastName))
    }

    private fun validateFirstName(firstName: String?): Boolean {
        if (firstName.isNullOrBlank())  {
            view?.setErrorOnFirstName("Prénom invalide")

            return false
        } else {
            view?.removeErrorFirstName()

            return true
        }
    }

    private fun validateLastName(lastName: String?): Boolean {
        if (lastName.isNullOrBlank())  {
            view?.setErrorOnLastName("Nom invalide")
            return false
        } else {
            view?.removeErrorLastName()

            return true
        }
    }
}