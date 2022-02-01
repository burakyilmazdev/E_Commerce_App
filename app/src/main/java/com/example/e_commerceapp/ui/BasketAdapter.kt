package com.example.e_commerceapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.data.models.Products
import com.example.e_commerceapp.databinding.BasketItemBinding
import com.example.e_commerceapp.databinding.ProductItemBinding
import com.squareup.picasso.Picasso

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private val basketProductList = arrayListOf<Products>()

    class BasketViewHolder(val binding: BasketItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem : Products){
            Picasso.get().load(productItem.url).into(binding.ivProductImage)
            binding.tvProductName.text = productItem.name
            binding.tvProductPrice.text = productItem.price
            binding.executePendingBindings()
        }

        companion object{
            fun create(parent:ViewGroup): BasketAdapter.BasketViewHolder {
                val binding = BasketItemBinding.inflate(LayoutInflater.from(parent.context))
                return BasketAdapter.BasketViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketProductList[position])
    }

    override fun getItemCount(): Int {
        return basketProductList.size
    }
}