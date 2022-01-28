package com.example.e_commerceapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.data.models.Products
import com.example.e_commerceapp.databinding.ProductItemBinding
import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductListViewHolder>(){

    private val productList = arrayListOf<Products>()

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(productList : List<Products>){
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }

    class ProductListViewHolder(private val binding : ProductItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bind(productItem : Products){
                Picasso.get().load(productItem.url).into(binding.ivProductImage)
                binding.tvProductName.text = productItem.name
                binding.tvProductPrice.text = productItem.price
                binding.executePendingBindings()
            }

        companion object{

            fun create(parent:ViewGroup):ProductListViewHolder{
                val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context))
                return ProductListViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}