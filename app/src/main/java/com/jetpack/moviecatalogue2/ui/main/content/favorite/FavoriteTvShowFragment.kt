package com.jetpack.moviecatalogue2.ui.main.content.favorite

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jetpack.moviecatalogue2.databinding.FragmentFavoriteTvShowBinding
import com.jetpack.moviecatalogue2.ui.main.content.tv.TvAdapter
import com.jetpack.moviecatalogue2.viewModel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class FavoriteTvShowFragment : DaggerFragment() {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: FragmentFavoriteTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvShowBinding.inflate(inflater)
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
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, {
            if (it != null) {
                binding.rvTvFav.adapter?.let { adapter ->
                    when (adapter) {
                        is TvAdapter -> {
                            if (it.isNullOrEmpty()) {
                                binding.rvTvFav.visibility = View.GONE
                                binding.empty.emptyLayout.visibility = View.VISIBLE
                            } else {
                                binding.rvTvFav.visibility = View.VISIBLE
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
        with(binding.rvTvFav) {
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(activity, 2)
                } else {
                    GridLayoutManager(activity, 4)
                }
            setHasFixedSize(true)
            adapter = TvAdapter()
        }
    }
}
