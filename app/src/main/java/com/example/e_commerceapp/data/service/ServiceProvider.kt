package com.example.e_commerceapp.data.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceProvider {

    val BASE_URL = "https://raw.githubusercontent.com/burakyilmazdev/JsonFile/main/"

    private val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    private val apiService = retrofit.create(ApiService::class.java)

    fun getApiService() : ApiService = apiService

}