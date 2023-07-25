package com.atakan.randomduck

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DuckApi {
    @GET("v2/quack")
    suspend fun getRandomDuck() : Response<Duck>
}