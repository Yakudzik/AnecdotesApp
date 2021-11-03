package com.example.anecdotesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.anecdotesapp.databinding.FragmentContentBinding
import com.example.anecdotesapp.paging.JokeLoadStateAdapter
import com.example.anecdotesapp.paging.ListAdapter
import com.example.anecdotesapp.room.AnecdoteDataBase
import com.example.anecdotesapp.room.BaseAnecdote
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


class ContentFragment : Fragment() {
    private lateinit var binding: FragmentContentBinding
    private lateinit var adapter: ListAdapter
    private val viewModel: AnecdoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContentBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = viewModel.title

        adapter = ListAdapter()
        //  concatAdapter = ConcatAdapter(adapter,JokeLoadStateAdapter(adapter))


        val recyclerView = binding.recyclerViewID
        // recyclerView.adapter = concatAdapter
        recyclerView.adapter = adapter.withLoadStateFooter(
            footer = JokeLoadStateAdapter { adapter.retry()}
        )

        adapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.apply {
                recyclerViewID.isVisible = state.refresh != LoadState.Loading
                progressBarID.isVisible = state.refresh == LoadState.Loading
            }
            state.source.refresh.endOfPaginationReached
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
//            throwable.printStackTrace()
//        }
        CoroutineScope(Dispatchers.IO ).launch {
            //    @OptIn(ExperimentalCoroutinesApi::class)

            viewModel.item.collectLatest {
                adapter.submitData(it)
                adapter.itemCount
            }
        }

    }
}