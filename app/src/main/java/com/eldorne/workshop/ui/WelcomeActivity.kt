package com.eldorne.workshop.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eldorne.workshop.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity()
{
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
    ** Life cycle
    ************************************************************************************************
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view of the current activity of default activity
        this.setContentView(R.layout.activity_welcome)

        setupView()
    }

    /*
    ************************************************************************************************
    ** Private method
    ************************************************************************************************
     */
    private fun setupView() {
        //Add a simple "click listener" on the button
        mStartButton.setOnClickListener {
            //Simply start the formActivity
            startActivity(Intent(this, FormActivity::class.java))
        }
    }
}
