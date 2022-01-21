package com.example.e_commerceapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductsFragment : Fragment() {

    private val serviceProvider = ServiceProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        serviceProvider
            .getApiService()
            .getNewProducts()
            .enqueue(object : Callback<List<Products>> {
                override fun onResponse(
                    call: Call<List<Products>>,
                    response: Response<List<Products>>
                ) {
                    Log.v("TEST", "Success: ${response.body().toString()}")

                }

                override fun onFailure(call: Call<List<Products>>, t: Throwable) {

                }

            })

        serviceProvider
            .getApiService()
            .getPopularProducts()
            .enqueue(object : Callback<List<Products>>{
                override fun onResponse(
                    call: Call<List<Products>>,
                    response: Response<List<Products>>
                ) {
                    Log.v("TEST", "Success: ${response.body().toString()}")
                }

                override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                    Log.v("FAILED", "Failed: ${t.localizedMessage.toString()}")
                }

            })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.new_products_fragment, container, false)
    }

    companion object {


    }

}


