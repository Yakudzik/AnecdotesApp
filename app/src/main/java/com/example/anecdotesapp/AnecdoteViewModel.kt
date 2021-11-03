package com.example.anecdotesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.anecdotesapp.paging.JokePagingSource
import com.example.anecdotesapp.retrofit.AnecdoteApi
import com.example.anecdotesapp.room.AnecdoteDataBase
import com.example.anecdotesapp.room.BaseAnecdote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okio.IOException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.await

class AnecdoteViewModel(app: Application) : AndroidViewModel(app) {

    private var _categoryNumber = MutableLiveData<Int>(0)
    val categoryNumber: LiveData<Int> = _categoryNumber

    private lateinit var joke: BaseAnecdote

    private lateinit var response: Response<String>

    var title = ""

    //db instance
    val instance = AnecdoteDataBase.getDatabase(app)

    //pager setup
    val item = Pager(
        PagingConfig(
            enablePlaceholders = true,
            pageSize = 10
        )
    ) { JokePagingSource(this) }.flow

    //get response and add answer 2 base
    private suspend fun getJokesResponse(): Boolean {
        val category = _categoryNumber.value

        response = Response.success(category?.let { AnecdoteApi.invoke().getJoke(it).await() })

        return if (response.isSuccessful ) {
            val result =createGoodString(response.body()!!)
             joke = BaseAnecdote(0, result)
            addJoke2Base(joke)
            true
        } else {
            Log.i("Response error", response.errorBody().toString())
            false
        }
    }

    //add content to base
    private fun addJoke2Base(j: BaseAnecdote) {
        viewModelScope.launch(Dispatchers.IO) {
            instance.anecdoteDao().addAnecdote(joke)
            //  Log.i("Add element", j.anecdoteStr)
        }
    }

    //get content pack
    suspend fun getNewContentPartResponse(): Boolean {
        return if (getJokesResponse()) {
            for (i in 0 until 10) {
                getJokesResponse()

            }
            true
        }else
            false
    }

    private fun createGoodString(string: String):String{
        var str = string.replace("{\"content\":\"","")
        return str.replace("\"}","")

    }

    fun setCategoryNum(categoryNum: Int) {
        _categoryNumber.postValue(categoryNum)
    }
}