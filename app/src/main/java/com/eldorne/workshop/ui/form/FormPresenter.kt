package com.eldorne.workshop.ui.form

import com.eldorne.workshop.ui.base.presenter.BasePresenter
import javax.inject.Inject

class FormPresenter
@Inject
constructor(/*Add needed dependencies here*/)
    : BasePresenter<FormView>() {

    /*
    ************************************************************************************************
    ** Public fun
    ************************************************************************************************
     */
    fun onItemHomeClick() {
        view?.stopProcess()
    }

    fun onAttemptValidation(firstName: String, lastName: String) {
        if (isInputValid(firstName, lastName)) {
            //Input is valid, start the main activity
            view?.navigateToMainActivity(firstName, lastName)

        }
    }

    /*
    ************************************************************************************************
    ** Private fun
    ************************************************************************************************
     */
    private fun isInputValid(firstName: String?,
                             lastName: String): Boolean {
        return (validateFirstName(firstName) && validateLastName(lastName))
    }

    private fun validateFirstName(firstName: String?): Boolean {
        if (firstName.isNullOrBlank()) {
            view?.setErrorOnFirstName("Prénom invalide")

            return false
        } else {
            view?.removeErrorOnFirstName()

            return true
        }
    }

    private fun validateLastName(lastName: String?): Boolean {
        if (lastName.isNullOrBlank()) {
            view?.setErrorOnLastName("Nom invalide")
            return false
        } else {
            view?.removeErrorOnLastName()

            return true
        }
    }
}