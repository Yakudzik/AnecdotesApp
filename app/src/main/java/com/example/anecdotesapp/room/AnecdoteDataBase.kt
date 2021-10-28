package com.example.anecdotesapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.room.AnecdoteDao
import com.example.pokedex.room.BaseAnecdote

@Database(entities = [BaseAnecdote::class], version = 1)
abstract class AnecdoteDataBase : RoomDatabase() {

    abstract fun pokemonDao(): AnecdoteDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AnecdoteDataBase? = null
//
//        fun getDatabase(context: Context): AnecdoteDataBase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null)
//                return tempInstance
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AnecdoteDataBase::class.java,
//                    "top_anecdotes"
//                ).fallbackToDestructiveMigration().build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}