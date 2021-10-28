package com.example.pokedex.room

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface AnecdoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAnecdote(joke: BaseAnecdote)

    @Delete
    suspend fun deleteOneElement(joke: BaseAnecdote)

    @Update
    suspend fun updateAnecdoteData(joke: BaseAnecdote): Int

    @Query("SELECT * FROM top_anecdotes ORDER BY id ASC")
    fun getAllData(): PagingSource<Int,BaseAnecdote>
}