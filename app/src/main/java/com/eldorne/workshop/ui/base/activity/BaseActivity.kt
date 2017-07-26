package com.eldorne.workshop.ui.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * [AppCompatActivity] implementation used the provide a simplification of the life cycle
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
    */
    /**
     * Will:
     * -initialise the view with [getLayoutResource]
     * -call [init]
     */
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        init(savedInstanceState)
    }

    /*
    ************************************************************************************************
    ** Fun every child will have to implement
    ************************************************************************************************
    */
    /**
     * get the layout id
     * it will be use under onCreate()
     * as setContentView(getLayoutResource());
     */
    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    /**
     * init the activity here
     * This method is equivalent to onCreate()
     */
    protected abstract fun init(savedInstanceState: Bundle?)
}