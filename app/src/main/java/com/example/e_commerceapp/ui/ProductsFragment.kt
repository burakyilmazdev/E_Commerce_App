package com.example.e_commerceapp.ui

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.ProductDataSource
import com.example.e_commerceapp.data.models.Products
import com.example.e_commerceapp.data.models.Status
import com.example.e_commerceapp.data.room.ProductDatabase
import com.example.e_commerceapp.data.room.ProductViewModel
import com.example.e_commerceapp.databinding.FragmentBasketBinding
import com.example.e_commerceapp.databinding.ProductsFragmentBinding
import io.reactivex.Flowable.combineLatest
import io.reactivex.Observable.combineLatest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProductsFragment : Fragment(),ProductsAdapter.Listener {


    private val productDataSource = ProductDataSource()
    private lateinit var binding: ProductsFragmentBinding
    private val newProductsAdapter = ProductsAdapter(this)
    private val popularProductsAdapter = ProductsAdapter(this)
    private lateinit var productDatabase:ProductDatabase
    private lateinit var productViewModel:ProductViewModel




    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        productDataSource
            .fetchNewProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it.status) {
                    Status.SUCCESS -> {
                        newProductsAdapter.setProductList(it.data!!)
                        binding.newProductProgressBar.visibility = View.GONE

                    }
                    Status.LOADING -> {
                        binding.newProductProgressBar.visibility = View.VISIBLE


                    }
                }
            }

        productDataSource.fetchPopularProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it.status) {
                    Status.SUCCESS -> {
                        popularProductsAdapter.setProductList(it.data!!)
                        binding.popularProductsProgressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.newProductProgressBar.visibility = View.VISIBLE
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productDatabase = ProductDatabase.getDatabase(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.products_fragment, container, false)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.popularProductsRecyclerView.adapter = popularProductsAdapter
        binding.newProductsRecyclerView.adapter = newProductsAdapter
        return binding.root
    }

    override fun onCLickItem(productItem: Products) {
        Toast.makeText(context, "${productItem.name} Sepete Eklendi! ", Toast.LENGTH_LONG).show()
        productViewModel.addProduct(productItem)

    }

}



