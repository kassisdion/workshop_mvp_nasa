package com.eldorne.workshop.ui.base.presenter

import android.support.annotation.CallSuper

open class BasePresenter<T : BaseView> {

    protected var view: T? = null
        get() = null
        private set

    @CallSuper
    fun onAttachView(view: T) {
        this.view = view
    }

    @CallSuper
    fun onDetachView() {
        view = null
    }

    val isViewAttached: Boolean
        get() = view != null

}
