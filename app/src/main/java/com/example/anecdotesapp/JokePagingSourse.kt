package com.example.anecdotesapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.room.BaseAnecdote

class JokePagingSourse : PagingSource<Int, BaseAnecdote>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseAnecdote> {
        TODO("Not yet implemented")
    }

    override fun getRefreshKey(state: PagingState<Int, BaseAnecdote>): Int? {
        TODO("Not yet implemented")
    }
}