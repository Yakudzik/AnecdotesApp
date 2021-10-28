package com.example.anecdotesapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AnecdoteApi {

    @GET("/RandJSON.aspx?CType={id}")
    suspend fun getJoke(@Path("id") number: Int): retrofit2.Call<String>

    companion object {
        fun invoke(): AnecdoteApi {
            //перехватчик для просмотра ответов сервера
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://rzhunemogu.ru/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AnecdoteApi::class.java)
        }
    }
}