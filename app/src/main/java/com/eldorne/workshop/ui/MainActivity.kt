package com.eldorne.workshop.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.eldorne.workshop.R
import com.eldorne.workshop.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    /*
    ************************************************************************************************
    ** Builder
    ************************************************************************************************
    */
    companion object {
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
    private val mTextviewIdentity by lazy {
        TextView_identity
    }

    private val mTopView by lazy {
        toolbar
    }

    private val mFirstName by lazy {
        intent.getStringExtra(INTENT_ARG_FIRSTNAME)
    }

    private val mLastName by lazy {
        intent.getStringExtra(INTENT_ARG_LASTNAME)
    }

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
    */


    override fun init(savedInstanceState: Bundle?) {
        setupView()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun getTopView(): View {
        return mTopView
    }

    /*
                ************************************************************************************************
                ** Private method
                ************************************************************************************************
                */
    private fun setupView() {
        mTextviewIdentity.text = "$mFirstName $mLastName"
    }
}
