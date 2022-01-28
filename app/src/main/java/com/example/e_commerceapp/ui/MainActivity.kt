package com.example.e_commerceapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.e_commerceapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
     private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.nav_home->{

                }
                R.id.nav_basket->{


                }


            }
            true
        }

    }
}