package com.eldorne.workshop.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.eldorne.workshop.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
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
