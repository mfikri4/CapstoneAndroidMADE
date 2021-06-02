package com.mfikri.capstoneandroidexpert.presentation.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfikri.capstoneandroidexpert.databinding.FragmentTvBinding
import com.mfikri.capstoneandroidexpert.presentation.viewmodel.FragViewModel
import com.mfikri.core.data.Resource
import com.mfikri.core.domain.model.Tv
import com.mfikri.core.ui.TvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment() {

    private lateinit var binding: FragmentTvBinding
    private val mViewModel: FragViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShow = mViewModel.getTv()
        val lAdapter = TvAdapter()
        tvShow.observe(viewLifecycleOwner, { tv ->
            if (tv != null) {
                when (tv) {
                    is Resource.Loading -> binding.pbMain.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pbMain.visibility = View.GONE
                        lAdapter.setData(tv.data)
                    }
                    is Resource.Error -> {
                        binding.pbMain.visibility = View.GONE
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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