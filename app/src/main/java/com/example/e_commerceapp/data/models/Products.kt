package com.example.e_commerceapp.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product_table")
data class Products(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val price: String?,
    val url: String?
){
    var quantity : Int = 0
}