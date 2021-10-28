package com.example.pokedex.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_anecdotes")
data class BaseAnecdote(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var anecdoteStr: String
)
