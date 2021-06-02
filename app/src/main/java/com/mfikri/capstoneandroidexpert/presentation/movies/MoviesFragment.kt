package com.mfikri.capstoneandroidexpert.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfikri.capstoneandroidexpert.databinding.FragmentMoviesBinding
import com.mfikri.capstoneandroidexpert.presentation.viewmodel.FragViewModel
import com.mfikri.core.data.Resource
import com.mfikri.core.domain.model.Movies
import com.mfikri.core.ui.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val mViewModel: FragViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = mViewModel.getMovies()
        val lAdapter = MoviesAdapter()

        movie.observe(viewLifecycleOwner, { mov ->
            if (mov != null) {
                when (mov) {
                    is Resource.Loading -> binding.pbMain.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pbMain.visibility = View.GONE
                        lAdapter.setData(mov.data)
                    }
                    is Resource.Error -> {
                        binding.pbMain.visibility = View.GONE
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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