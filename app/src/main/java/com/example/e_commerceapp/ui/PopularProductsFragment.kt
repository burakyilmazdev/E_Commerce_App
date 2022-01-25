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
import com.example.e_commerceapp.databinding.PopularProductsFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PopularFragment : Fragment() {

    private val productDataSource = ProductDataSource()
    private lateinit var binding : PopularProductsFragmentBinding
   // private val popularProductsAdapter = newProductsAdapter()
    private val popularProductsAdapter = ProductsAdapter()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productDataSource
            .fetchPopularProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it.status){
                    Status.SUCCESS ->{
                        popularProductsAdapter.setProductList(it.data!!)
                        binding.popularProductProgressBar.visibility = View.GONE
                    }
                    Status.LOADING ->{
                        binding.popularProductProgressBar.visibility = View.VISIBLE
                    }


                }
            }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.popular_products_fragment,container,false)
        binding.popularProductsRecyclerView.adapter = popularProductsAdapter
        return binding.root
    }


}