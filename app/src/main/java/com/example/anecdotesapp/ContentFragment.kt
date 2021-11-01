package com.example.anecdotesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.anecdotesapp.databinding.FragmentContentBinding
import com.example.anecdotesapp.paging.ListAdapter
import com.example.anecdotesapp.room.AnecdoteDataBase
import com.example.anecdotesapp.room.BaseAnecdote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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

        adapter = ListAdapter()
        var recyclerView = binding.recyclerViewID
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
          //  viewModel.getRepeatResponse(30)
            viewModel.item.collectLatest {
                adapter.submitData(it)
            }
        }

        return binding.root
    }
}