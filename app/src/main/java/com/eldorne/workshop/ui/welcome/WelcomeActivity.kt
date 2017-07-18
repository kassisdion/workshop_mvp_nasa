package com.eldorne.workshop.ui.welcome

import android.content.Intent
import android.os.Bundle
import com.eldorne.workshop.R
import com.eldorne.workshop.WorkshopApplication
import com.eldorne.workshop.ui.base.activity.BaseActivity
import com.eldorne.workshop.ui.form.FormActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import javax.inject.Inject

class WelcomeActivity : BaseActivity(), WelcomeView {
    /*
    ************************************************************************************************
    ** Private field
    ************************************************************************************************
     */
    //With Lazy we are going to create it as a non-nullable property
    //and will be executed just when you use it and just the first time.
    private val mStartButton by lazy {
        welcomeActivity_startButton
    }

    /*
    ************************************************************************************************
    ** Injection
    ************************************************************************************************
     */
    @Inject
    lateinit var mPresenter: WelcomePresenter

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    override fun getLayoutResource(): Int {
        return R.layout.activity_welcome
    }

    override fun init(savedInstanceState: Bundle?) {
        setupPresenter()
        setupView()
    }

    /*
    ************************************************************************************************
    ** WelcomeView implementation
    ************************************************************************************************
     */
    override fun navigateToFormActivity() {
        startActivity(Intent(this, FormActivity::class.java))
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

    private fun setupView() {
        //Add a simple "click listener" on the button
        mStartButton.setOnClickListener {
            //Simply start the formActivity
            mPresenter.onStartClicked()
        }
    }
}
