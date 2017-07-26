package com.eldorne.workshop.extension

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Provide an overload of [Call.enqueue] for less verbosity
 *
 *    Old syntax:
 *    ##############################################################################################
 *    # .enqueue(object : Callback<T> {
 *    #      override fun onResponse(call: Call<T>?, response: Response<T>?) {
 *    #          //Do what you want with the response
 *    #      }
 *    #
 *    #      override fun onFailure(call: Call<T>?, t: Throwable?) {
 *    #          //Something went wrong
 *    #      })
 *    ##############################################################################################
 *
 *    New Syntax:
 *    ##############################################################################################
 *    # .enqueue(
 *    #      success = {
 *    #           response ->
 *    #           //Do what you want with the response
 *    #      },
 *    #     failure = {
 *    #         t ->
 *    #          //Something went wrong
 *    #
 *    #     })
 *    ##############################################################################################
 *
 */
fun <T> Call<T>.enqueue(success: (response: Response<T>?) -> Unit,
                        failure: (t: Throwable?) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            success(response)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            failure(t)
        }

    })
}