package com.mfikri.capstoneandroidexpert.presentation.tv

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mfikri.capstoneandroidexpert.R
import com.mfikri.capstoneandroidexpert.databinding.ActivityDetailTvBinding
import com.mfikri.capstoneandroidexpert.presentation.viewmodel.DetailViewModel
import com.mfikri.core.data.Resource
import com.mfikri.core.domain.model.Tv
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvBinding
    private val mViewModel: DetailViewModel by viewModels()
    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraDetail = intent.extras
        if (extraDetail != null) {
            val tvId = extraDetail.getInt(EXTRA_DETAIL)

            mViewModel.setSelected(tvId)
            mViewModel.getDetailTv()
            val detailMov = mViewModel.getDetailTv()

            detailMov.observe(this, { detail ->
                if (detail != null) {
                    when (detail) {
                        is Resource.Loading -> binding.pbMain.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbMain.visibility = View.GONE
                            mViewModel.setDataTv(detail.data!!)
                            getDetail(detail.data!!)
                            state = detail.data!!.fav
                            setFavoriteState(state)
                        }
                        is Resource.Error -> {
                            binding.pbMain.visibility = View.GONE
                            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        binding.fabFavorite.setOnClickListener {
            mViewModel.setFavoriteTv()
            setFavoriteState(state)
            setStatusFav(state)
        }
    }

    private fun getDetail(detailTvDetail: Tv) {
        binding.tvTitle.text = detailTvDetail.name
        binding.tvDate.text = detailTvDetail.first_air_date
        binding.tvDescription.text = detailTvDetail.overview
        binding.tvRating.text = detailTvDetail.vote_average.toString()
        binding.tvCountRating.text = detailTvDetail.vote_count.toString()
        val urlPoster = "https://image.tmdb.org/t/p/w500/${detailTvDetail.poster_path}"
        Glide.with(this)
            .load(urlPoster)
            .apply(
                RequestOptions().placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.ivFoto)
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.fabFavorite.setImageResource(R.drawable.ic_fav)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_fav_border)
        }
    }

    private fun setStatusFav(fav: Boolean) {
        if (fav) {
            Toast.makeText(this, R.string.removeData, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.addData, Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}