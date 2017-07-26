package com.eldorne.workshop.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBar
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.eldorne.workshop.R
import com.eldorne.workshop.WorkshopApplication
import com.eldorne.workshop.ui.base.activity.BaseFullScreenActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseFullScreenActivity(), MainActivityView, SwipeRefreshLayout.OnRefreshListener {

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
    lateinit var mAdapter: ArrayAdapter<String>

    private val mSwipeRefreshLayout by lazy {
        SwipeRefreshLayout_spaceObject
    }

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

    private val mRefreshButton: View? by lazy {
        btn_refresh
    }

    private val mLoadingIndicator: View? by lazy {
        ContentLoadingProgressBar_mainActivity
    }

    private val mlistView: ListView? by lazy {
        listview_spaceObject
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
        setupView()
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

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading(start: Boolean) {
        mLoadingIndicator?.visibility = if (start) View.VISIBLE else View.GONE
    }

    override fun showRefreshing(start: Boolean) {
        mSwipeRefreshLayout.isRefreshing = start
    }

    override fun populateSpaceObject(data: List<String>) {
        mAdapter.addAll(data)
    }

    override fun clearData() {
        mAdapter.clear()
    }

    /*
    ************************************************************************************************
    ** SwipeRefreshLayout.OnRefreshListener implementation
    ************************************************************************************************
     */
    override fun onRefresh() {
        mPresenter.onRefreshClicked()
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

    private fun setupView() {
        //Setup fab
        mRefreshButton?.setOnClickListener { view ->
            mPresenter.onRefreshClicked()
        }

        //Setup listView
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        mlistView?.adapter = mAdapter

        //Setup swipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { mPresenter.onItemHomeClick() })


        mSwipeRefreshLayout.setOnRefreshListener(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(mToolbar)
        mActionbar?.setDisplayHomeAsUpEnabled(true)
    }
}
