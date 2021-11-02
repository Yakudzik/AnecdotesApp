package com.example.anecdotesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.room.Room
import com.example.anecdotesapp.databinding.FragmentContentBinding
import com.example.anecdotesapp.paging.JokeLoadStateAdapter
import com.example.anecdotesapp.paging.ListAdapter
import com.example.anecdotesapp.room.AnecdoteDataBase
import com.example.anecdotesapp.room.BaseAnecdote
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


class ContentFragment : Fragment() {
    lateinit var binding: FragmentContentBinding
    lateinit var adapter: ListAdapter
    private val viewModel: AnecdoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContentBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = viewModel.title

        adapter = ListAdapter()

        var recyclerView = binding.recyclerViewID
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = JokeLoadStateAdapter(adapter),
            footer = JokeLoadStateAdapter(adapter)
        )

        adapter.addLoadStateListener { state:CombinedLoadStates ->
            binding.recyclerViewID.isVisible = state.refresh != LoadState.Loading
            binding.progressBarID.isVisible = state.refresh == LoadState.Loading

        }

        CoroutineScope(Dispatchers.IO).launch {
            //    @OptIn(ExperimentalCoroutinesApi::class)

            viewModel.item.collectLatest {
                adapter.submitData(it)
                adapter.itemCount
            }
        }

        return binding.root
    }
}