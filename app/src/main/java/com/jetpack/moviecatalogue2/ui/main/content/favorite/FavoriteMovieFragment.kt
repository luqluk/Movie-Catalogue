package com.jetpack.moviecatalogue2.ui.main.content.favorite

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jetpack.moviecatalogue2.databinding.FragmentFavoriteMovieBinding
import com.jetpack.moviecatalogue2.ui.main.content.movie.MovieAdapter
import com.jetpack.moviecatalogue2.viewModel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class FavoriteMovieFragment : DaggerFragment() {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteMovieBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            prepareRecycler()
            parentFragment?.let {
                viewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
            }
            observeFavoriteMovies()
        }
    }

    private fun observeFavoriteMovies() {
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, {
            if (it != null) {
                binding.rvMovieFav.adapter?.let { adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            if (it.isNullOrEmpty()) {
                                binding.rvMovieFav.visibility = View.GONE
                                binding.emptyMovie.emptyLayout.visibility = View.VISIBLE
                            } else {
                                binding.rvMovieFav.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }

    private fun prepareRecycler() {
        with(binding.rvMovieFav) {
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(activity, 2)
                } else {
                    GridLayoutManager(activity, 4)
                }
            setHasFixedSize(true)
            adapter = MovieAdapter()
        }
    }

}