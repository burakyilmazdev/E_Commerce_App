package com.example.e_commerceapp.ui


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.models.Products
import com.example.e_commerceapp.databinding.BasketItemBinding
import com.example.e_commerceapp.databinding.FragmentBasketBinding
import com.example.e_commerceapp.databinding.ProductItemBinding
import com.squareup.picasso.Picasso

class BasketAdapter(private val listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productList = emptyList<Products>()


    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(productList : List<Products>){
        this.productList = productList
        notifyDataSetChanged()
    }

    class BasketViewHolder(private val basketItemBinding : BasketItemBinding,private val basketFragmentBinding:FragmentBasketBinding)
        :RecyclerView.ViewHolder(basketItemBinding.root){

        @SuppressLint("SetTextI18n")
        fun bind(basketItem: Products, listener:Listener){
            basketItemBinding.tvProductName.text = basketItem.name
            basketItemBinding.tvProductPrice.text = basketItem.price+" TL"
            Picasso.get().load(basketItem.url).into(basketItemBinding.ivProductImage)
            basketItemBinding.button.setOnClickListener(View.OnClickListener {
                listener.onCLickItem(basketItem)        //delete
            })
            basketItemBinding.executePendingBindings()

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BasketViewHolder(BasketItemBinding.inflate(LayoutInflater.from(parent.context)),
            FragmentBasketBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BasketViewHolder->{
                holder.bind(productList[position],listener)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.v("TEST","getItem : ${productList.size}")
        return productList.size
    }

    interface Listener{
        fun onCLickItem(productItem: Products)
    }


}