package com.example.e_commerceapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceProvider {

    val BASE_URL = "https://raw.githubusercontent.com/burakyilmazdev/JsonFile/main/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    private val apiService = retrofit.create(ApiService::class.java)

    fun getApiService() : ApiService = apiService

}