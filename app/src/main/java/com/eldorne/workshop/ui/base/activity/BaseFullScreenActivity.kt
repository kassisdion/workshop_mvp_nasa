package com.eldorne.workshop.ui.base.activity

import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.View
import com.eldorne.workshop.helper.WindowsHelper

abstract class BaseFullScreenActivity : BaseActivity() {

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    @CallSuper
    override fun init(savedInstanceState: Bundle?) {
        WindowsHelper.setStatusBarTranslucent(window, getTopView(), true)
    }

    /*
    ************************************************************************************************
    ** Fun every child will have to implement
    ************************************************************************************************
    */
    protected abstract fun getTopView(): View
}