package com.example.anecdotesapp.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.anecdotesapp.retrofit.AnecdoteApi
import com.example.anecdotesapp.room.AnecdotsDao
import com.example.anecdotesapp.room.BaseAnecdote

class JokePagingSourse() : PagingSource<Int, BaseAnecdote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseAnecdote> {
        TODO("Not yet implemented")

    }

    override fun getRefreshKey(state: PagingState<Int, BaseAnecdote>): Int? {
        TODO("Not yet implemented")
    }
}