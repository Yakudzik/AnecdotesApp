package com.example.anecdotesapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AnecdoteApi {

    @GET("/RandJSON.aspx")
      fun getJoke(@Query("CType") number: Int): Call<String>

    companion object {
        fun invoke(): AnecdoteApi {
            //перехватчик для просмотра ответов сервера
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://rzhunemogu.ru/")
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(AnecdoteApi::class.java)
        }
    }
}