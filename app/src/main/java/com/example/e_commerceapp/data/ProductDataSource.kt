package com.example.e_commerceapp.data

import android.annotation.SuppressLint
import com.example.e_commerceapp.data.models.Products
import com.example.e_commerceapp.data.models.Resource
import com.example.e_commerceapp.data.request.ServiceProvider
import io.reactivex.Observable
import io.reactivex.functions.Consumer

class ProductDataSource {

    private val serviceProvider = ServiceProvider()

    @SuppressLint("CheckResult")
    fun fetchNewProducts():Observable<Resource<List<Products>>>{
        return Observable.create { emitter->
            emitter.onNext(Resource.loading())

            serviceProvider.
                getApiService()
                .getNewProducts()
                .subscribe(
                    { newProductsList->
                        emitter.onNext(Resource.success(newProductsList))
                        emitter.onComplete()

                    },
                    {error->
                        emitter.onNext(Resource.error(error.message?:""))
                        emitter.onComplete()

                })
        }
    }

    @SuppressLint("CheckResult")
    fun fetchPopularProducts():Observable<Resource<List<Products>>>{
        return  Observable.create { emitter->
        emitter.onNext(Resource.loading())


        serviceProvider
            .getApiService()
            .getPopularProducts()
            .subscribe(
                { popularProductsList ->
                    emitter.onNext(Resource.success(popularProductsList))
                    emitter.onComplete()
                },
                {
                    emitter.onNext(Resource.error(it.message))
                    emitter.onComplete()
                })
        }
    }




}