package com.eldorne.workshop.ui.base.presenter

import android.support.annotation.CallSuper

open class BasePresenter<T : BaseView> {
    /*
    ************************************************************************************************
    ** Protected field
    ************************************************************************************************
     */
    /**
     * View linked with the presenter (or null if the presenter is detached)
     */
    protected var view: T? = null
        private set

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    /**
     * Should be called for attaching the presenter with a [BaseView]
     */
    @CallSuper
    fun onAttachView(view: T) {
        this.view = view
    }

    /**
     * Should be called for detaching the presenter from a [BaseView]
     */
    @CallSuper
    fun onDetachView() {
        view = null
    }

    /*
    ************************************************************************************************
    ** Utils for child
    ************************************************************************************************
     */
    /**
     * Simple helper use to known if the presenter is linked with a [BaseView] or not
     */
    val isViewAttached: Boolean
        get() = view != null

}
