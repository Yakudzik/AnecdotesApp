package com.example.anecdotesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.anecdotesapp.databinding.FragmentMenuBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

        (activity as AppCompatActivity).supportActionBar?.title = "Test app"

        //clear base in menu
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.instance.anecdoteDao().nukeTable()
        }

        binding.button1ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Анекдот"
                    viewModel.setCategoryNum(1)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button2ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Рассказы"
                    viewModel.setCategoryNum(2)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button3ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Стишки"
                    viewModel.setCategoryNum(3)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button4ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Афоризмы"
                    viewModel.setCategoryNum(4)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button5ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Цитаты"
                    viewModel.setCategoryNum(5)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button6ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Тосты"
                    viewModel.setCategoryNum(6)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button8ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Статусы"
                    viewModel.setCategoryNum(8)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button11ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Анекдот 18+"
                    viewModel.setCategoryNum(11)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button12ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Рассказы 18+"
                    viewModel.setCategoryNum(12)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button13ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Стишки 18+"
                    viewModel.setCategoryNum(13)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button14ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Афоризмы 18+"

                    viewModel.setCategoryNum(14)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button15ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Цитаты 18+"
                    viewModel.setCategoryNum(15)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button16ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Тосты 18+"
                    viewModel.setCategoryNum(16)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button18ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Статусы 18+"
                    viewModel.setCategoryNum(18)
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }

        return binding.root
    }


}