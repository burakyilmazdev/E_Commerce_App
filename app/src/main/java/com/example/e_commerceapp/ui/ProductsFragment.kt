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
import com.example.e_commerceapp.data.models.Status
import com.example.e_commerceapp.databinding.ProductsFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProductsFragment : Fragment() {


    private val productDataSource = ProductDataSource()
    private lateinit var binding : ProductsFragmentBinding
    private val newProductsAdapter = ProductsAdapter()
    private val popularProductsAdapter = ProductsAdapter()


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

        productDataSource.
        fetchPopularProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it.status){
                    Status.SUCCESS ->{
                        popularProductsAdapter.setProductList(it.data!!)
                        binding.popularProductsProgressBar.visibility = View.GONE
                    }
                    Status.LOADING->{
                        binding.newProductProgressBar.visibility = View.VISIBLE
                        Log.v("TEST","Loading")

                    }
                }
            }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.products_fragment,container,false)

        binding.popularProductsRecyclerView.adapter = popularProductsAdapter
        binding.newProductsRecyclerView.adapter = newProductsAdapter

        return binding.root
    }
}


