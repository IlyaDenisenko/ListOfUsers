package com.task.listofusers

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://randomuser.me/api/"

    private fun getRetrofit(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
    val retrofitApi: RetrofitApi
        get() = getRetrofit(BASE_URL).create(RetrofitApi::class.java)
}

interface RetrofitApi {
    @GET("?results=10")
    suspend fun getUserList(): Response<UsersModel>

    @GET("1.4/")
    suspend fun getUsersBySeed(@Query("seed")value: String, @Query("results")results: Long): Response<UsersModel>
}
