package com.example.e_commerceapp.data.models

data class Resource<out T>(val status: Status, val data:T?, val msg:String? ){

    companion object{
        fun <T> success(data:T?): Resource<T> {
            return Resource(Status.SUCCESS,data,null)
        }
        fun <T> error(msg: String?): Resource<T> {
            return Resource(Status.ERROR,null,msg)
        }
        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING,null,null)
        }



    }
}