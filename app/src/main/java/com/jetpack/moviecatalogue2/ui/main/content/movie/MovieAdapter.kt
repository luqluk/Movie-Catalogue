package com.jetpack.moviecatalogue2.ui.main.content.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.jetpack.moviecatalogue2.BuildConfig.URL_IMAGE
import com.jetpack.moviecatalogue2.R
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.databinding.ItemMovieBinding
import com.jetpack.moviecatalogue2.ui.detail.DetailActivity
import com.jetpack.moviecatalogue2.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MovieAdapter :
    PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as MovieEntity)

    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                if (movie.title != null) {
                    tvTitle.text = movie.title
                } else {
                    tvTitle.text = movie.titleShow
                }
                tvScore.text = movie.rate.toString()
                tvVotes.text = movie.vote.toString()

                val multi = MultiTransformation(
                    RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.TOP)
                )
                Glide.with(itemView.context)
                    .load(URL_IMAGE + movie.poster)
                    .apply(RequestOptions.bitmapTransform(multi))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPhoto)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(EXTRA_MOVIE, movie.movieId)
                itemView.context.startActivity(intent)
            }

        }
    }
}