package com.eldorne.workshop.ui.main

import android.util.Log
import com.eldorne.workshop.api.NasaApi
import com.eldorne.workshop.api.response.FeedResponse
import com.eldorne.workshop.extension.enqueue
import com.eldorne.workshop.ui.base.presenter.BasePresenter
import retrofit2.Response
import javax.inject.Inject

class MainPresenter
@Inject
constructor(private val nasaApi: NasaApi)
    : BasePresenter<MainActivityView>() {

    /*
    ************************************************************************************************
    ** Public fun
    ************************************************************************************************
    */
    fun setupView(firsName: String, lastName: String) {
        view?.populateName("""$firsName $lastName""")
        retrieveFeedAndPopulate(false)
    }

    fun onItemHomeClick() {
        view?.stopProcess()
    }

    fun onRefreshClicked() {
        retrieveFeedAndPopulate(true)
    }

    /*
    ************************************************************************************************
    ** Private fun
    ************************************************************************************************
    */
    private fun retrieveFeedAndPopulate(isRefreshing: Boolean) {
        showAnimation(isRefreshing, true)

        nasaApi.getFeed("DEMO_KEY", null, null).enqueue(
                success = {
                    response ->
                    showAnimation(isRefreshing, false)
                    onFeedResponseSuccess(response)
                },
                failure = {
                    t ->
                    Log.d("TAG", "retrieveCharacter > e= $t")
                    showAnimation(isRefreshing, false)
                    view?.showError(t?.message)
                })
    }

    private fun showAnimation(isRefreshing: Boolean,
                              start: Boolean) {
        when (isRefreshing) {
            true -> view?.showRefreshing(start)
            false -> view?.showLoading(start)
        }
    }

    private fun onFeedResponseSuccess(response: Response<FeedResponse>?) {
        response?.body()?.objects?.let {
            view?.clearData()
            val data: ArrayList<String> = ArrayList()

            it.forEach {
                data.add("#####${it.key}#####")
                it.value.forEach { data.add("* ${it.name}") }
            }

            view?.populateSpaceObject(data)
        }
    }
}