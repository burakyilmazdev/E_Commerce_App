package com.example.e_commerceapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.ProductDataSource
import com.example.e_commerceapp.data.models.Status
import com.example.e_commerceapp.databinding.NewProductsFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProductsFragment : Fragment() {


    private val productDataSource = ProductDataSource()
    private lateinit var binding : NewProductsFragmentBinding
    private val newProductsAdapter = ProductsAdapter()


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productDataSource.
        fetchNewProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it.status){
                    Status.SUCCESS ->{
                        newProductsAdapter.setProductList(it.data!!)
                        binding.newProductProgressBar.visibility = View.GONE
                    }
                    Status.LOADING->{
                        binding.newProductProgressBar.visibility = View.VISIBLE

                    }
                }
            }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.new_products_fragment,container,false)

        binding.newProductsRecyclerView.adapter = newProductsAdapter
        return binding.root
    }



}


