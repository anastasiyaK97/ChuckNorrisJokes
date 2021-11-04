package com.example.hdhgtestjokes.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hdhgtestjokes.AndroidApplication
import com.example.hdhgtestjokes.MainActivity
import com.example.hdhgtestjokes.R
import com.example.hdhgtestjokes.data.api.InternetConnectionCheckable
import com.example.hdhgtestjokes.databinding.JokesFragmentBinding
import com.example.hdhgtestjokes.domain.Joke
import com.example.hdhgtestjokes.presentation.extensions.setActivityTitle
import com.example.hdhgtestjokes.presentation.viewmodel.JokesViewModel
import com.example.hdhgtestjokes.presentation.viewmodel.ViewModelFactory
import org.koin.android.ext.android.inject

class JokesFragment : Fragment(), InternetConnectionCheckable {

    companion object {
        fun newInstance() = JokesFragment()
    }

    private lateinit var binding: JokesFragmentBinding
    private lateinit var viewModel: JokesViewModel
    private lateinit var recyclerAdapter: JokeRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = JokesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory: ViewModelFactory by inject()
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(JokesViewModel::class.java)
        binding.reloadButton.setOnClickListener {
            if (isInternetAvailable()) {
                viewModel.reload(Integer.parseInt(binding.jokesCount.text.toString()))
            } else {
                if (activity is MainActivity) {
                    (activity as MainActivity).requestLayouts()
                }
            }
        }
        viewModel.countValue.observe(viewLifecycleOwner, {
            if (it == null) {
                binding.jokesCount.text.clear()
            } else {
                binding.jokesCount.setText(it.toString())
            }
        })

        initRecyclerView(binding.jokesList)
        viewModel.jokeList.observe(viewLifecycleOwner, {
            recyclerAdapter.setData(it)
        })

    }

    override fun onResume() {
        super.onResume()
        setActivityTitle(R.string.app_name)
    }

    private fun initRecyclerView(jokesList: RecyclerView) {
        with(jokesList) {
            layoutManager = LinearLayoutManager(context)
            this@JokesFragment.recyclerAdapter =
                JokeRecyclerViewAdapter((viewModel.jokeList.value ?: ArrayList()) as List<Joke>)
            adapter = this@JokesFragment.recyclerAdapter
        }
    }

}