package com.eldorne.workshop.di.modules

import com.eldorne.workshop.BuildConfig
import com.eldorne.workshop.api.NasaApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    /*
    ************************************************************************************************
    ** Public dependencies for injection
    ************************************************************************************************
     */
    /**
     * Create a new instance of [AnilistApi]
     *
     * @param retrofitBuilder provided by [provideDefaultRetrofit]
     * @param okhtttpClientBuilder provided by [provideDefaultClient]
     */
    @Provides
    @Singleton
    fun provideApi(retrofitBuilder: Retrofit.Builder,
                   okHttpClientBuilder: OkHttpClient.Builder): NasaApi {

        /*Add interceptor for completing header*/
        okHttpClientBuilder
                .interceptors()
                .add(0, Interceptor { chain ->
                    val originalRequest = chain.request()
                    val authenticatedRequest = originalRequest
                            .newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .method(originalRequest.method(), originalRequest.body())
                            .build()

                    chain.proceed(authenticatedRequest)
                })

        /*Build API*/
        return retrofitBuilder
                .baseUrl(NasaApi.BASE_URL)
                /*Set the converter (response->Json)*/
                .addConverterFactory(GsonConverterFactory.create())
                /*Set client (used to send request)*/
                .client(okHttpClientBuilder.build())
                .build()
                .create(NasaApi::class.java)
    }

    /*
    ************************************************************************************************
    ** Internal dependencies
    ************************************************************************************************
     */
    /**
     * create a [retrofit2.Retrofit.Builder] with a default configuration :
     */
    @Provides
    @Singleton
    fun provideDefaultRetrofit(): Retrofit.Builder {
        val builder = Retrofit.Builder()

        //Could add more stuff like a default converter strategy

        return builder
    }

    /**
     * Create a [okhttp3.OkHttpClient.Builder] with a default configuration:
     */
    @Provides
    @Singleton
    fun provideDefaultClient(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        //Set custom time out
        builder
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)

        //Add interceptor for showing request in log
        // /!\ log shouldn't be displayed in a production environement /!\
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }


        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("accept", "contentType/json")
            chain.proceed(request.build())
        }

        //Could add more stuff like a cache strategy

        return builder
    }

}