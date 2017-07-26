package com.eldorne.workshop.api

import com.eldorne.workshop.api.response.FeedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET(FEED)
    fun getFeed(@Query("api_key") apiKey: String,
                @Query("start_date") startDate: String?,
                @Query("end_date") endDate: String?): Call<FeedResponse>

    companion object Constant {
        const val BASE_URL = "https://api.nasa.gov/"
        const val FEED = "neo/rest/v1/feed"
    }
}
