package com.example.e_commerceapp.data.service

import com.example.e_commerceapp.data.models.Products
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("NewProducts.json")
    fun getNewProducts():Single<List<Products>>

    @GET("PopulerProducts.json")
    fun getPopularProducts():Single<List<Products>>



}