package com.example.hdhgtestjokes.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hdhgtestjokes.databinding.JokeRowBinding
import com.example.hdhgtestjokes.domain.Joke

class JokeRecyclerViewAdapter(private var list: List<Joke>) :
    RecyclerView.Adapter<JokeRecyclerViewAdapter.JokeViewHolder>() {

    class JokeViewHolder(private val binding: JokeRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Joke) {
            binding.jokeText.text = item.getText()
        }
    }

    fun setData(newList: List<Joke?>?) {
        newList?.let {
            this.list = (it as List<Joke>?)!!
            this.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JokeRowBinding.inflate(inflater, parent, false)
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val currentJoke = list[position]
        holder.bind(currentJoke)
    }

    override fun getItemCount(): Int = list.size
}