package com.jetpack.moviecatalogue2.ui.main.content.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jetpack.moviecatalogue2.databinding.FragmentFavoriteBinding
import com.jetpack.moviecatalogue2.viewModel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class FavoriteFragment : DaggerFragment() {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { setupViewPager(it) }
        viewModel = ViewModelProvider(this@FavoriteFragment, factory)[FavoriteViewModel::class.java]

    }

    private fun setupViewPager(context: Context) {
        val sectionsPagerAdapter = SectionsPagerAdapter(context, childFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}