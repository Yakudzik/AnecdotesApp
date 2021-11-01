package com.example.anecdotesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.anecdotesapp.databinding.FragmentMenuBinding
import kotlinx.coroutines.launch


class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding
    val viewModel: AnecdoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.button1ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getJokesResponse(1)
                //findNavController( ).navigate(R.id.contentFragment)
            }
        }

        return binding.root
    }


}