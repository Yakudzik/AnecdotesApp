package com.example.anecdotesapp.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.anecdotesapp.databinding.ErrorItemBinding
import com.example.anecdotesapp.databinding.ListItemBinding
import java.util.jar.Pack200.Packer.ERROR
import java.util.jar.Pack200.Packer.PROGRESS

class JokeLoadStateAdapter(private val adapter: ListAdapter) :
    LoadStateAdapter<JokeLoadStateAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
                ErrorItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), adapter
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) = holder.bind(loadState)

    class ViewHolder(binding: ErrorItemBinding, adapter: ListAdapter) :
        RecyclerView.ViewHolder(binding.root) {


        private val progressBar = binding.progressBar
        private val message = binding.errorMessageID
        private val button = binding.retryButtonID
//            .also {
//            it.setOnClickListener {
//                adapter.retry()
//            }
//        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                message.text = loadState.error.localizedMessage
            }

            progressBar.isVisible = loadState is LoadState.Loading
            button.isVisible = loadState is LoadState.Error
            message.isVisible = loadState is LoadState.Error
        }

    }
}