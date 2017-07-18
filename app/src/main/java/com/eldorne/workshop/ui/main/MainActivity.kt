package com.eldorne.workshop.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.MenuItem
import android.view.View
import com.eldorne.workshop.R
import com.eldorne.workshop.WorkshopApplication
import com.eldorne.workshop.ui.base.activity.BaseFullScreenActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseFullScreenActivity(), MainActivityView {

    /*
    ************************************************************************************************
    ** Builder
    ************************************************************************************************
    */
    companion object Builder {
        private val INTENT_ARG_FIRSTNAME = "MainActivity.INTENT_ARG_FIRSTNAME"
        private val INTENT_ARG_LASTNAME = "MainActivity.INTENT_ARG_LASTNAME"

        fun newIntent(context: Context,
                      firstName: String,
                      lastName: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(INTENT_ARG_FIRSTNAME, firstName)
            intent.putExtra(INTENT_ARG_LASTNAME, lastName)
            return intent
        }
    }

    /*
    ************************************************************************************************
    ** Private field
    ************************************************************************************************
    */
    private val mToolbar by lazy {
        toolbar
    }

    private val mFirstName by lazy {
        intent.getStringExtra(INTENT_ARG_FIRSTNAME)
    }

    private val mLastName by lazy {
        intent.getStringExtra(INTENT_ARG_LASTNAME)
    }

    private val mActionbar: ActionBar? by lazy {
        supportActionBar
    }

    /*
    ************************************************************************************************
    ** Injection
    ************************************************************************************************
    */
    @Inject
    lateinit var mPresenter: MainPresenter

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
    */
    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        setupPresenter()
        setupToolbar()
        mPresenter.setupView(mFirstName, mLastName)
    }

    override fun getTopView(): View {
        return mToolbar
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                //User click on back arrow, just finish
                mPresenter.onItemHomeClick()
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*
    ************************************************************************************************
    ** MainActivityView implementation
    ************************************************************************************************
    */
    override fun populateName(name: String) {
        mActionbar?.title = name
    }

    override fun stopProcess() {
        finish()
    }

    /*
    ************************************************************************************************
    ** Private method
    ************************************************************************************************
    */
    private fun setupPresenter() {
        WorkshopApplication.workshopComponent.inject(this)
        mPresenter.onAttachView(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(mToolbar)
        mActionbar?.setDisplayHomeAsUpEnabled(true)
    }
}
