package com.eldorne.workshop.ui.welcome

import com.eldorne.workshop.ui.base.BasePresenter

/**
 * Created by eldorne on 11/07/17.
 */

class WelcomPresenter: BasePresenter<WelcomeView>() {

    public fun onStartClick() {
        view?.navigateToFormActivity()
    }

}