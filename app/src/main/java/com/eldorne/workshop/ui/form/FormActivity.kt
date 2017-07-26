package com.eldorne.workshop.ui.form

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.MenuItem
import com.eldorne.workshop.R
import com.eldorne.workshop.WorkshopApplication
import com.eldorne.workshop.ui.base.activity.BaseActivity
import com.eldorne.workshop.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_form_content.*
import javax.inject.Inject

class FormActivity : BaseActivity(), FormView {

    /*
    ************************************************************************************************
    ** Private field
    ************************************************************************************************
    */
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
    ** Injection
    ************************************************************************************************
    */
    @Inject
    lateinit var mPresenter: FormPresenter

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    override fun getLayoutResource(): Int {
        return R.layout.activity_form
    }

    override fun init(savedInstanceState: Bundle?) {
        setupPresenter()
        setupToolbar()
        setupView()
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
    ** FormView implementation
    ************************************************************************************************
     */
    override fun navigateToMainActivity(firstName: String, lastName: String) {
        val intent = MainActivity.newIntent(this, firstName, lastName)
        startActivity(intent)
    }

    override fun setErrorOnFirstName(error: String) {
        mTextInputLayoutFirstName.isErrorEnabled = true
        mTextInputLayoutFirstName.error = error
    }

    override fun removeErrorOnFirstName() {
        mTextInputLayoutFirstName.isErrorEnabled = false
        mTextInputLayoutFirstName.error = null
    }

    override fun removeErrorOnLastName() {
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

    /*
    ************************************************************************************************
    ** Private fun
    ************************************************************************************************
     */
    private fun setupPresenter() {
        WorkshopApplication.workshopComponent.inject(this)
        mPresenter.onAttachView(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(mtoolbar)
        mActionbar?.title = "Décline ton identité"
        mActionbar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupView() {
        mButton.setOnClickListener { _ ->
            val firstName = mTextInputLayoutFirstName.editText?.text.toString()
            val lastName = mTextInputLayoutLastName.editText?.text.toString()

            mPresenter.onAttemptValidation(firstName, lastName)
        }
    }
}
