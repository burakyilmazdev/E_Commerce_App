package com.example.e_commerceapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.ProductDataSource
import com.example.e_commerceapp.data.request.ServiceProvider
import com.example.e_commerceapp.databinding.NewProductsFragmentBinding
import com.example.e_commerceapp.databinding.PopularProductsFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class ProductsFragment : Fragment() {


    val productDataSource = ProductDataSource()
    private lateinit var binding : NewProductsFragmentBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productDataSource.
        fetchNewProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.v("TEST","${it.data.toString()}")
            }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_products_fragment, container, false)
    }



}


