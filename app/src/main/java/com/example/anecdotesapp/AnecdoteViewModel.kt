package com.example.anecdotesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.anecdotesapp.retrofit.AnecdoteApi
import com.example.anecdotesapp.room.AnecdoteDataBase
import com.example.anecdotesapp.room.BaseAnecdote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.await

class AnecdoteViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var joke: BaseAnecdote

    lateinit var response: Response<String>

    val instance = Room.databaseBuilder(
        app,
        AnecdoteDataBase::class.java,
        "top_anecdotes"
    ).fallbackToDestructiveMigration().build()


    val item = Pager(
        PagingConfig(
            pageSize = 50
        )
    ) { instance.anecdoteDao().getAllData() }.flow


    suspend fun getJokesResponse(category: Int) {
        response = Response.success(AnecdoteApi.invoke().getJoke(category).await())

        if (response.isSuccessful) {
            val result = response.body()
             joke = BaseAnecdote(0, result.toString())
             addJoke2Base(joke)

        }
    }

    private fun addJoke2Base(j: BaseAnecdote) {
        viewModelScope.launch(Dispatchers.IO) {
            instance.anecdoteDao().addAnecdote(joke)
            Log.i("Add element", j.anecdoteStr)

        }
    }

    suspend fun getNewContentPartResponse(int: Int, category:Int) {
        for (i in 0 until int) {
            getJokesResponse(category)
        }
    }
}