package com.example.e_commerceapp.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.models.Products
import com.example.e_commerceapp.data.room.ProductViewModel
import com.example.e_commerceapp.databinding.ActivityMainBinding
import com.example.e_commerceapp.databinding.FragmentBasketBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.math.BigDecimal
import java.text.DecimalFormat


class BasketFragment : Fragment(), BasketAdapter.Listener {
    private lateinit var binding: FragmentBasketBinding
    private val basketAdapter = BasketAdapter(this)
    private lateinit var productViewModel: ProductViewModel
    private lateinit var mainBinding: ActivityMainBinding
    var basketElementNumber = 0



    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        binding.BasketRv.adapter = basketAdapter

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.basketProducts.observe(viewLifecycleOwner, Observer {

            var basketSum = 0
            for (element in it) {
                basketSum += element.price?.toInt()!!
            }
            binding.basketSum.text = basketSum.toString() + " TL"
            basketAdapter.setProductList(it)

        })

        return binding.root
    }

    override fun onCLickItem(productItem: Products) {
        Toast.makeText(context, "${productItem.name} Sepetten Silindi! ", Toast.LENGTH_SHORT).show()
        productViewModel.deleteProduct(productItem)
    }


}


