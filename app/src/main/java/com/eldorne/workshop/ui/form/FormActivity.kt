package com.eldorne.workshop.ui.form

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.MenuItem
import com.eldorne.workshop.R
import com.eldorne.workshop.ui.MainActivity
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_form_content.*

class FormActivity : AppCompatActivity(), FormView {


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

    private val mPresenter by lazy {
        FormPresenter()
    }

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        mPresenter.onAttach(this)

        setupToolbar()
        setupView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
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

            mPresenter.onAttemptValidation(firstName, lastName)
        }
    }

    override fun navigateToMainActivity(firstName: String, lastName: String) {
        val intent = MainActivity.newIntent(this, firstName, lastName)
        startActivity(intent)
    }

    override fun setErrorOnFirstName(error: String) {
        mTextInputLayoutFirstName.isErrorEnabled = true
        mTextInputLayoutFirstName.error = error
    }
    override fun removeErrorFirstName()
    {
        mTextInputLayoutFirstName.isErrorEnabled = false
        mTextInputLayoutFirstName.error = null
    }


    override fun removeErrorLastName()
    {
        mTextInputLayoutLastName.isErrorEnabled = false
        mTextInputLayoutLastName.error = null
    }
    override fun setErrorOnLastName(error: String) {
        mTextInputLayoutLastName.isErrorEnabled = true
        mTextInputLayoutLastName.error = error
    }

    override fun stopProcess() {
        finish()
    }
}
