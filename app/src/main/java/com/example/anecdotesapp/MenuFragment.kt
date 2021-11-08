package com.example.anecdotesapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.anecdotesapp.databinding.FragmentMenuBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding
    private val viewModel: AnecdoteViewModel by activityViewModels()
    lateinit var preference: SharedPreferences
    lateinit var savedCategoryTitle: String
    var savedCategoryNumber by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        preference = requireActivity().getSharedPreferences("sharedP", Context.MODE_PRIVATE)
        savedCategoryTitle = preference.getString("CATEGORY_KEY", "Main menu").toString()
        savedCategoryNumber = preference.getInt("CATEGORY_NUM", 0)

        (activity as AppCompatActivity).supportActionBar?.title = savedCategoryTitle


        CoroutineScope(Dispatchers.IO).launch {
            viewModel.instance.anecdoteDao().nukeTable()
        }

        binding.button1ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Анекдот"
                    viewModel.setCategoryNum(1)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button2ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Рассказы"

                    viewModel.setCategoryNum(2)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button3ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Стишки"

                    viewModel.setCategoryNum(3)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button4ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Афоризмы"

                    viewModel.setCategoryNum(4)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button5ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Цитаты"

                    viewModel.setCategoryNum(5)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button6ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Тосты"

                    viewModel.setCategoryNum(6)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button8ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Статусы"

                    viewModel.setCategoryNum(8)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button11ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Анекдот 18+"

                    viewModel.setCategoryNum(11)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button12ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Рассказы 18+"

                    viewModel.setCategoryNum(12)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button13ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Стишки 18+"

                    viewModel.setCategoryNum(13)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button14ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Афоризмы 18+"

                    viewModel.setCategoryNum(14)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button15ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Цитаты 18+"

                    viewModel.setCategoryNum(15)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button16ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Тосты 18+"

                    viewModel.setCategoryNum(16)
                    saveData(viewModel.title, viewModel.getCategoryNum())

                })
                findNavController().navigate(R.id.contentFragment)
            }
        }
        binding.button18ID.setOnClickListener {
            lifecycleScope.launch {
                viewModel.categoryNumber.observe(viewLifecycleOwner, {
                    viewModel.title = "Статусы 18+"
                    viewModel.setCategoryNum(18)
                    saveData(viewModel.title, viewModel.getCategoryNum())
                })
                findNavController().navigate(R.id.contentFragment)
            }
        }

        return binding.root
    }

    private fun saveData(savedString: String, savedInt: Int) {
        preference =
            requireActivity().getSharedPreferences("sharedP", Context.MODE_PRIVATE) ?: return
        with(preference.edit()) {
            putString("CATEGORY_KEY", savedString)
            putInt("CATEGORY_NUM", savedInt)
            apply()
        }
    }
}