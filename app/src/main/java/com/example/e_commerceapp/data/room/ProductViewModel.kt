package com.example.e_commerceapp.data.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.data.models.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application):AndroidViewModel(application) {

    val basketProducts : LiveData<List<Products>>
    private val repository:ProductRepository

    init {
        val productDao = ProductDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        basketProducts = repository.allProducts
    }


    fun addProduct(product : Products){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }

    fun updateProduct(product : Products){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(product)
        }
    }



    fun deleteProduct(product: Products){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(product)
        }
    }
}