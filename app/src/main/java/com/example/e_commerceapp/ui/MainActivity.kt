package com.example.e_commerceapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.room.ProductViewModel
import com.example.e_commerceapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val navController = findNavController(this,R.id.fragmentContainerView)
        findViewById<BottomNavigationView>(R.id.bottomNav).setupWithNavController(navController)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNavigationView.getOrCreateBadge(R.id.basketFragment).apply {
            productViewModel.basketProducts.observe(this@MainActivity, Observer {
                number = it.size
            })

        }


    }
}