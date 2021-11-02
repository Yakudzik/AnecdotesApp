package com.example.anecdotesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.anecdotesapp.paging.JokePagingSourse
import com.example.anecdotesapp.retrofit.AnecdoteApi
import com.example.anecdotesapp.room.AnecdoteDataBase
import com.example.anecdotesapp.room.BaseAnecdote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.await

class AnecdoteViewModel(app: Application) : AndroidViewModel(app) {

    private var _categoryNumber = MutableLiveData<Int>(0)
    val categoryNumber: LiveData<Int> = _categoryNumber

    private lateinit var joke: BaseAnecdote

    private lateinit var response: Response<String>

//    val instance = Room.databaseBuilder(
//        app,
//        AnecdoteDataBase::class.java,
//        "top_anecdotes"
//    ).fallbackToDestructiveMigration().build()

    val instance = AnecdoteDataBase.getDatabase(app)

    val item = Pager(
        PagingConfig(
            enablePlaceholders = true,
            pageSize = 10
        )
    ) { JokePagingSourse(this) }.flow

    suspend fun getJokesResponse() {
       val category = _categoryNumber.value
        response = Response.success(category?.let { AnecdoteApi.invoke().getJoke(it).await() })

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

    suspend fun getNewContentPartResponse(  ) {
        for (i in 0 until 10) {
            getJokesResponse( )
        }
    }
    fun setCategoryNum(categoryNum:Int){
        _categoryNumber.postValue(categoryNum)
    }
}