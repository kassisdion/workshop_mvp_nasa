package com.eldorne.workshop.ui.welcome

import com.eldorne.workshop.ui.base.presenter.BasePresenter
import javax.inject.Inject

class WelcomePresenter
@Inject
constructor(/*Add needed dependencies here*/)
    : BasePresenter<WelcomeView>() {

    fun onStartClicked() {
        view?.navigateToFormActivity()
    }
}