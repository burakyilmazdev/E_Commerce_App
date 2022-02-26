package com.example.e_commerceapp.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.models.Products
import com.example.e_commerceapp.data.room.ProductDatabase
import com.example.e_commerceapp.databinding.FragmentBasketBinding
import com.example.e_commerceapp.databinding.ProductsFragmentBinding


class BasketFragment : Fragment(){
    private lateinit var binding: FragmentBasketBinding
    private val basketAdapter = BasketAdapter()
    private var productList = arrayListOf<Products>()
    private lateinit var productDatabase: ProductDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        binding.BasketRv.adapter = basketAdapter
        return binding.root
    }

}