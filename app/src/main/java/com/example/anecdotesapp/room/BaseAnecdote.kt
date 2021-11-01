package com.example.anecdotesapp.room

import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_anecdotes")
data class BaseAnecdote(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "content") var anecdoteStr: String
)
