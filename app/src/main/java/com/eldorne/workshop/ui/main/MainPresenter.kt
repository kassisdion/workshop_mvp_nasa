package com.eldorne.workshop.ui.main

import com.eldorne.workshop.ui.base.presenter.BasePresenter
import javax.inject.Inject

class MainPresenter
@Inject
constructor(/*Add needed dependencies here*/)
    : BasePresenter<MainActivityView>() {

    fun setupView(firsName: String, lastName: String) {
        view?.populateName("""$firsName $lastName""")
    }

    fun onItemHomeClick() {
        view?.stopProcess()
    }
}