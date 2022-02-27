package com.example.e_commerceapp.data.room

import androidx.lifecycle.LiveData
import com.example.e_commerceapp.data.models.Products

class ProductRepository(private val productDao : ProductDao) {

    val allProducts : LiveData<List<Products>> = productDao.getProducts()

    suspend fun addProduct(product: Products){
        productDao.addProduct(product)
    }

    suspend fun deleteProduct(product: Products){
        productDao.delete(product)
    }

}