package com.eldorne.workshop.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.MenuItem
import com.eldorne.workshop.R
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_form_content.*

class FormActivity : AppCompatActivity() {
    /*
    ************************************************************************************************
    ** Private field
    ************************************************************************************************
    */
    //With Lazy we are going to create it as a non-nullable property
    //and will be executed just when you use it and just the first time.
    private val mtoolbar by lazy {
        toolbar
    }

    private val mTextInputLayoutFirstName by lazy {
        TextInputLayout_firstName
    }

    private val mTextInputLayoutLastName by lazy {
        TextInputLayout_lastName
    }

    private val mButton by lazy {
        button_default_activity_validate
    }

    private val mActionbar: ActionBar? by lazy {
        supportActionBar
    }

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        setupToolbar()
        setupView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                //User click on back arrow, just finish
                finish()
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    private fun setupToolbar() {
        setSupportActionBar(mtoolbar)
        mActionbar?.title = "Décline ton identité"
        mActionbar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupView() {
        mButton.setOnClickListener { x ->
            val firstName = mTextInputLayoutFirstName.editText?.text.toString()
            val lastName = mTextInputLayoutLastName.editText?.text.toString()

            if (isInputValid(firstName, lastName)) {
                //Input is valid, start the main activity
                val intent = MainActivity.newIntent(this, firstName, lastName)
                startActivity(intent)
            }
        }
    }

    private fun isInputValid(firstName: String?,
                             lastName: String): Boolean {
        return (validateFirstName(firstName) && validateLastName(lastName))
    }

    private fun validateFirstName(firstName: String?): Boolean {
        if (firstName.isNullOrBlank())  {
            mTextInputLayoutFirstName.isErrorEnabled = true
            mTextInputLayoutFirstName.error = "Prénom invalide"

            return false
        } else {
            mTextInputLayoutFirstName.isErrorEnabled = false
            mTextInputLayoutFirstName.error = null

            return true
        }
    }

    private fun validateLastName(lastName: String?): Boolean {
        if (lastName.isNullOrBlank())  {
            mTextInputLayoutLastName.isErrorEnabled = true
            mTextInputLayoutLastName.error = "Nom invalide"

            return false
        } else {
            mTextInputLayoutLastName.isErrorEnabled = false
            mTextInputLayoutLastName.error = null

            return true
        }
    }
}
