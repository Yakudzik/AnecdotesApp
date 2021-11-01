package com.example.anecdotesapp.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.anecdotesapp.databinding.ListItemBinding
import com.example.anecdotesapp.room.BaseAnecdote

class ListAdapter : PagingDataAdapter<BaseAnecdote, ListAdapter.MyViewHolder>(DIFF_CALBACK) {

    private val LOG_TAG = "listAdapter"

    companion object {
        private var DIFF_CALBACK = object : DiffUtil.ItemCallback<BaseAnecdote>() {
            override fun areItemsTheSame(oldItem: BaseAnecdote, newItem: BaseAnecdote)
                    : Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BaseAnecdote, newItem: BaseAnecdote)
                    : Boolean = oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem: BaseAnecdote? = getItem(position)

        holder.bunTo(currentItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d(LOG_TAG, "create viewHolder")
        return MyViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class MyViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var nameView = binding.listItemTextviewID

        var joke: BaseAnecdote? = null

        fun bunTo(baseAnecdote: BaseAnecdote?) {
            joke = baseAnecdote
            nameView.text = baseAnecdote!!.anecdoteStr
        }
    }
}