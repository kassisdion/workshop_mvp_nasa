package com.eldorne.workshop.ui.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.eldorne.workshop.helper.WindowsHelper

/**
 * Created by eldorne on 11/07/17.
 */

abstract class BaseActivity : AppCompatActivity() {


    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
    */
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        WindowsHelper.setStatusBarTranslucent(window, getTopView(), true)
        init(savedInstanceState)
    }

    @LayoutRes
    protected abstract fun getLayoutResource() : Int


    protected abstract fun getTopView() : View

    protected abstract fun init(savedInstanceState: Bundle?)
}