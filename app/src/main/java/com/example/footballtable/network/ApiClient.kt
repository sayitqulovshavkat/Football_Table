package com.example.footballtable.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://api-football-standings.azharimm.site/"

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}