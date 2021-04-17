package com.example.moviekotlin.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val upcomingMoveApi : String = "https://api.themoviedb.org/"

object APIClient {

    val instance : APIService = Retrofit.Builder().run {

        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()

        baseUrl(upcomingMoveApi)

        addConverterFactory(GsonConverterFactory.create())

        client(createRequestInterceptorClient())

        build()

    }.create(APIService::class.java)

    private fun createRequestInterceptorClient(): OkHttpClient {

        val interceptor = Interceptor{  chain ->

            val origin = chain.request()

            val requestBuilder = origin.newBuilder()

            val request = requestBuilder.build()

            chain.proceed(request)

        }


        return OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .build();



    }
}