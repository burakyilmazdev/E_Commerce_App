package com.example.e_commerceapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("NewProducts.json")
    fun getNewProducts():Call<List<Products>>

    @GET("PopulerProducts.json")
    fun getPopularProducts():Call<List<Products>>
}