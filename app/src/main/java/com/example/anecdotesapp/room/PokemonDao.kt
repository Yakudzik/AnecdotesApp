package com.example.pokedex.room

import androidx.lifecycle.LiveData
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
    fun getAllData(): LiveData<List<BaseAnecdote>>
}