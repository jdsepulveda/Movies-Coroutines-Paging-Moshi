package com.movies_coroutines.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.movies_coroutines.R
import com.movies_coroutines.databinding.PopularMovieItemBinding
import com.movies_coroutines.remote.model.Movie

class PopularMoviesAdapter(
    private val clickListener: (Movie) -> Unit
) : PagedListAdapter<Movie, PopularMoviesVH>(UserDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: PopularMovieItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.popular_movie_item, parent, false)
        return PopularMoviesVH(binding)
    }

    override fun onBindViewHolder(holder: PopularMoviesVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class PopularMoviesVH(private val binding: PopularMovieItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, clickListener: (Movie) -> Unit) {
        binding.textTitle.text = movie.title
        binding.cardMovie.setOnClickListener {
            clickListener(movie)
        }
    }
}