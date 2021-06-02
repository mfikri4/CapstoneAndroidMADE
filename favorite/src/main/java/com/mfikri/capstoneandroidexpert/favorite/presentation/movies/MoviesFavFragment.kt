package com.mfikri.capstoneandroidexpert.favorite.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfikri.capstoneandroidexpert.di.FavoriteModuleDependencies
import com.mfikri.capstoneandroidexpert.favorite.databinding.FragmentMoviesFavBinding
import com.mfikri.capstoneandroidexpert.favorite.di.DaggerFavoriteComponent
import com.mfikri.capstoneandroidexpert.favorite.presentation.FavoriteViewModel
import com.mfikri.capstoneandroidexpert.favorite.presentation.viewmodel.ViewModelFactory
import com.mfikri.capstoneandroidexpert.presentation.movies.DetailMoviesActivity
import com.mfikri.core.domain.model.Movies
import com.mfikri.core.ui.MoviesAdapter
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class MoviesFavFragment : Fragment() {
    private lateinit var binding: FragmentMoviesFavBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val mViewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(), FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .injectFav(this)
        super.onViewCreated(view, savedInstanceState)

        val movie = mViewModel.getMoviesFavorite()
        val lAdapter = MoviesAdapter()

        movie.observe(viewLifecycleOwner, { mov ->
            binding.pbMain.visibility = View.GONE

            if (mov.isEmpty()) binding.repositoryEmpty.visibility = View.VISIBLE
            else binding.repositoryEmpty.visibility = View.GONE

            if (mov.isEmpty()) binding.txtRepo.visibility = View.VISIBLE
            else binding.txtRepo.visibility = View.GONE

            lAdapter.setData(mov)
            lAdapter.notifyDataSetChanged()
        })

        with(binding.rvFragmentMovies) {
            layoutManager = LinearLayoutManager(context)
            adapter = lAdapter
        }

        lAdapter.setOnItemClickCallback(object : MoviesAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movies) {
                val detailMove = Intent(activity, DetailMoviesActivity::class.java)
                detailMove.putExtra(DetailMoviesActivity.EXTRA_DETAIL, movie.id)
                startActivity(detailMove)
            }
        })
    }
}