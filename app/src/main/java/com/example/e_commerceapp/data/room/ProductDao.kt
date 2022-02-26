package com.example.e_commerceapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.e_commerceapp.data.models.Products

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun getProducts(): LiveData<List<Products>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(Product : Products)

    @Delete
    fun delete(product:Products)

}