package com.example.e_commerceapp.ui


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.models.Products
import com.example.e_commerceapp.databinding.BasketItemBinding
import com.example.e_commerceapp.databinding.ProductItemBinding
import com.squareup.picasso.Picasso

class BasketAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productList = arrayListOf<Products>()


    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(productList : List<Products>){
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }

    class BasketViewHolder(private val basketItemBinding : BasketItemBinding):RecyclerView.ViewHolder(basketItemBinding.root){

        fun bind(basketItem: Products){
            basketItemBinding.tvProductName.text = basketItem.name
            basketItemBinding.tvProductPrice.text = basketItem.price
            Picasso.get().load(basketItem.url).into(basketItemBinding.ivProductImage)
            basketItemBinding.executePendingBindings()

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BasketViewHolder(BasketItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BasketViewHolder->{
                holder.bind(productList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        Log.v("TEST","getItem : ${productList.size}")
        return productList.size
    }


}