package com.example.anecdotesapp.room

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface AnecdotsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAnecdote(joke: BaseAnecdote)

    @Delete
    fun deleteOneElement(joke: BaseAnecdote)

    @Query("DELETE FROM top_anecdotes")
    fun nukeTable()

    @Update
    fun updateAnecdoteData(joke: BaseAnecdote)

    @Query("SELECT * FROM top_anecdotes LIMIT 10 OFFSET :index")
    fun getTenElements(index: Int): List<BaseAnecdote>

    @Query("SELECT * FROM top_anecdotes ORDER BY id ASC")
    // fun getAllData(): LiveData<List<BaseAnecdote>>
    fun getAllData(): PagingSource<Int, BaseAnecdote>

}