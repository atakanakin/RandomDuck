package com.atakan.randomduck

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitMain {

    private val baseUrl = "https://random-d.uk/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}