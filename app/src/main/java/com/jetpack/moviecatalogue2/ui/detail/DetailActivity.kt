package com.jetpack.moviecatalogue2.ui.detail

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.jetpack.moviecatalogue2.BuildConfig.URL_IMAGE
import com.jetpack.moviecatalogue2.R
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.databinding.ActivityDetailBinding
import com.jetpack.moviecatalogue2.viewModel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity(){

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = ""

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_MOVIE, 0)
        val tvId = intent.getIntExtra(EXTRA_TV, 0)

        showLoading(true)
        if (id != 0) {
            observeDetailMovie(id)
        } else {
            observeDetailTvShow(tvId)
        }
    }

    private fun observeDetailMovie(movieId: Int) {
        detailViewModel.detailMovie(movieId).observe(this, {
            setView(it, null)
        })
    }

    private fun observeDetailTvShow(tvShowId: Int) {
        detailViewModel.detailTv(tvShowId).observe(this, {
            it?.let {
                setView(null, it)
            }
        })
    }

    private fun setView(movie: MovieEntity?, tv: TvEntity?) {
        val image = movie?.poster ?: tv?.poster
        val score = movie?.rate ?: tv?.rate
        val vote = movie?.vote ?: tv?.vote
        val statusFavorite = movie?.isFavorite ?: tv?.isFavorite


        with(binding) {
            if (movie?.title != null) {
                detailTitle.text = movie.title
            } else {
                detailTitle.text = tv?.titleShow
            }

            detailOverview.text = movie?.overview ?: tv?.overview
            detailScore.text = score.toString()
            detailVote.text = vote.toString()
            languange.text = movie?.language ?: tv?.language

            Glide.with(this@DetailActivity)
                .load(URL_IMAGE + image)
                .apply(
                    RequestOptions
                        .placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(backgroundImgDetail)
        }

        showLoading(false)

        binding.backgroundImgDetail.setOnClickListener {
            val img = AlertDialog.Builder(this@DetailActivity)
            val show = ImageView(this@DetailActivity)
            Glide.with(this@DetailActivity)
                .load(URL_IMAGE + image)
                .into(show)
            img.setCustomTitle(show)
            val dialog: Dialog = img.create()
            dialog.show()
        }

        statusFavorite?.let { status ->
            setFavoriteState(status)
        }

        binding.btnFavorite.setOnClickListener {
            setFavorite(movie, tv)
        }
    }

    private fun setFavoriteState(status: Boolean){
        binding.apply {
            if (status) {
                btnFavorite.setImageResource(R.drawable.favorite)
            } else {
                btnFavorite.setImageResource(R.drawable.unfavorite)
            }
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvEntity?) {
        if (movie != null) {
            if (movie.isFavorite){
                showSnackBar("${movie.title} Removed from favorite")
            }else {
                showSnackBar("${movie.title} Added to favorite")
            }
            detailViewModel.setFavoriteMovie(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    showSnackBar("${tvShow.titleShow} Removed from favorite")
                }else {
                    showSnackBar("${tvShow.titleShow} Added from favorite")
                }
                detailViewModel.setFavoriteTvShow(tvShow)
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            binding.pBarDetail.visibility = View.VISIBLE
            binding.mainLayout.visibility = View.GONE
        } else {
            binding.mainLayout.visibility = View.VISIBLE
            binding.pBarDetail.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }
}