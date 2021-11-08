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
import retrofit2.HttpException
import java.lang.Exception

class JokePagingSource(private val viewModel: AnecdoteViewModel) :
    PagingSource<Int, BaseAnecdote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseAnecdote> {

        return try {
            val pagePosition = params.key ?: 0
            Log.i("pagePosition", pagePosition.toString())
            if (viewModel.getNewContentPartResponse()) {
                var index = pagePosition * 10
                val loadSize = viewModel.instance.anecdoteDao().getTenElements(index)
                LoadResult.Page(
                    data = loadSize,
                    prevKey = if (pagePosition == 0) null else pagePosition - 1,
                    nextKey = if (loadSize.isNullOrEmpty()) null else pagePosition + 1
                )
            } else
                LoadResult.Error(error("Retrofit response isn't good"))
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BaseAnecdote>): Int? = null
}