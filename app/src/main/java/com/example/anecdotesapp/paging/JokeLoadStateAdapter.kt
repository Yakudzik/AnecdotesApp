package com.example.anecdotesapp.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.anecdotesapp.databinding.ErrorItemBinding


class JokeLoadStateAdapter(private val retryCallback: () -> Unit) :
    LoadStateAdapter<JokeLoadStateAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {

        return ViewHolder(
            ErrorItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) = holder.bind(loadState)

    inner class ViewHolder(private val binding: ErrorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButtonID.setOnClickListener { retryCallback.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.Loading) {
                    progressBar.isVisible = true
                    retryButtonID.isVisible = false
                    errorMessageID.isVisible = false
                } else {
                    progressBar.isVisible = false
                    retryButtonID.isVisible = loadState is LoadState.Error
                    errorMessageID.isVisible = loadState is LoadState.Error
                }
            }
        }
    }
}