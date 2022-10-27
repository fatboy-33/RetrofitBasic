package com.example.retrofitbasic.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
//    fun getInstance(): Retrofit {
//        var mHttpLoggingInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        var mOkHttpClient = OkHttpClient
//            .Builder()
//            .addInterceptor(mHttpLoggingInterceptor)
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl("https://reqres.in")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(mOkHttpClient)
//            .build()
//    }

    val api : ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}