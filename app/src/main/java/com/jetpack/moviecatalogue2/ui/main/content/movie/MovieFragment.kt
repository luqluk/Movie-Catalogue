package com.jetpack.moviecatalogue2.ui.main.content.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jetpack.moviecatalogue2.databinding.FragmentMovieBinding
import com.jetpack.moviecatalogue2.viewModel.ViewModelFactory
import com.jetpack.moviecatalogue2.vo.Status
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MovieFragment : DaggerFragment() {

    private lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: FragmentMovieBinding

    private lateinit var mAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            mAdapter = MovieAdapter()

            prepareRecycler()
            activity?.let { setUpViewModel(it) }

            viewModel.getMovies().observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        mAdapter.submitList(it.data)
                        showLoading(false)
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.ERROR -> {
                        showErrorMessage(it.message as String)
                        showLoading(false)
                    }
                }
            })


        }
    }

    private fun setUpViewModel(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[MovieViewModel::class.java]
    }

    private fun prepareRecycler() {
        with(binding.rvMovie) {
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(activity, 2)
                } else {
                    GridLayoutManager(activity, 4)
                }
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }


    private fun showLoading(state: Boolean) {
        binding.apply {
            if (state) {
                shimmerViewContainer.visibility = View.VISIBLE
                shimmerViewContainer.startShimmer()
            } else {
                shimmerViewContainer.stopShimmer()
                shimmerViewContainer.visibility = View.GONE
            }
        }
    }

    private fun showErrorMessage(s: String) {
        FancyToast.makeText(requireContext(), s, FancyToast.LENGTH_LONG, FancyToast.ERROR, false)
            .show()
    }
}