package com.mfikri.capstoneandroidexpert.favorite.presentation.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfikri.capstoneandroidexpert.di.FavoriteModuleDependencies
import com.mfikri.capstoneandroidexpert.favorite.databinding.FragmentTvFavBinding
import com.mfikri.capstoneandroidexpert.favorite.di.DaggerFavoriteComponent
import com.mfikri.capstoneandroidexpert.favorite.presentation.FavoriteViewModel
import com.mfikri.capstoneandroidexpert.favorite.presentation.viewmodel.ViewModelFactory
import com.mfikri.capstoneandroidexpert.presentation.tv.DetailTvActivity
import com.mfikri.core.domain.model.Tv
import com.mfikri.core.ui.TvAdapter
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class TvFavFragment : Fragment() {
    private lateinit var binding: FragmentTvFavBinding

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
        binding = FragmentTvFavBinding.inflate(layoutInflater, container, false)
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
            .injectTv(this)
        super.onViewCreated(view, savedInstanceState)

        val tvShow = mViewModel.getTvFavorite()
        val lAdapter = TvAdapter()

        tvShow.observe(viewLifecycleOwner, { tv ->
            binding.pbMain.visibility = View.GONE

            if (tv.isEmpty()) binding.repositoryEmpty.visibility = View.VISIBLE
            else binding.repositoryEmpty.visibility = View.GONE

            if (tv.isEmpty()) binding.txtRepo.visibility = View.VISIBLE
            else binding.txtRepo.visibility = View.GONE

            lAdapter.setData(tv)
            lAdapter.notifyDataSetChanged()
        })

        with(binding.rvFragmentTv) {
            layoutManager = LinearLayoutManager(context)
            adapter = lAdapter
        }

        lAdapter.setOnItemClickCallback(object : TvAdapter.OnItemClickCallback {
            override fun onItemClicked(tv: Tv) {
                val detailMove = Intent(activity, DetailTvActivity::class.java)
                detailMove.putExtra(DetailTvActivity.EXTRA_DETAIL, tv.id)
                startActivity(detailMove)
            }
        })
    }
}
