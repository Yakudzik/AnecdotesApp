package com.example.anecdotesapp.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.anecdotesapp.AnecdoteViewModel
import com.example.anecdotesapp.retrofit.AnecdoteApi
import com.example.anecdotesapp.room.AnecdotsDao
import com.example.anecdotesapp.room.BaseAnecdote

class JokePagingSourse( val viewModel: AnecdoteViewModel) : PagingSource<Int, BaseAnecdote>() {

//    private companion object {
//        const val INITIAL_PAGE_INDEX = 0
//    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseAnecdote> {
        val pagePosition = params.key ?: 0
        Log.i("pagePosition", pagePosition.toString())
        viewModel.getNewContentPartResponse()
        var index = pagePosition*10
        val loadSize = viewModel.instance.anecdoteDao().getTenElements(index)
        return LoadResult.Page(
            data = loadSize,
            prevKey = if (pagePosition == 0) null else pagePosition - 1,
            nextKey = if (loadSize.isNullOrEmpty()) null else pagePosition + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, BaseAnecdote>): Int? = null
}