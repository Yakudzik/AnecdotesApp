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

    @Update
      fun updateAnecdoteData(joke: BaseAnecdote): Int

    @Query("SELECT * FROM top_anecdotes WHERE id=:id ")
    fun getOneElement(id: Int ): BaseAnecdote

    @Query("SELECT * FROM top_anecdotes ORDER BY id ASC")
   // fun getAllData(): LiveData<List<BaseAnecdote>>
    fun getAllData(): PagingSource<Int, BaseAnecdote>

}