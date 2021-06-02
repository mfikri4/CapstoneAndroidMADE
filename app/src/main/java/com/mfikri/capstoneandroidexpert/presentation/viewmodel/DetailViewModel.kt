package com.mfikri.capstoneandroidexpert.presentation.viewmodel

import androidx.lifecycle.*
import com.mfikri.core.data.Resource
import com.mfikri.core.domain.model.Movies
import com.mfikri.core.domain.model.Tv
import com.mfikri.core.domain.usecase.WatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val watchUseCase: WatchUseCase) : ViewModel() {

    val id = MutableLiveData<Int>()
    private val detailMovies = MutableLiveData<Movies>()
    private val detailTv = MutableLiveData<Tv>()

    fun getDetailMovie(): LiveData<Resource<Movies>> = Transformations.switchMap(id) { mov ->
        watchUseCase.getMovieDetail(mov).asLiveData()
    }

    fun getDetailTv(): LiveData<Resource<Tv>> = Transformations.switchMap(id) { tv ->
        watchUseCase.getTvDetail(tv).asLiveData()
    }

    fun setSelected(id: Int) {
        this.id.value = id
    }

    fun setDataMovies(detailMovies: Movies) {
        this.detailMovies.value = detailMovies
    }

    fun setDataTv(detailTv: Tv) {
        this.detailTv.value = detailTv
    }

    fun setFavoriteMovie() {
        val moviesData = detailMovies.value
        if (moviesData != null) {
            val state = !moviesData.fav
            watchUseCase.setMoviesFavorite(moviesData, state)
        }
    }

    fun setFavoriteTv() {
        val tvData = detailTv.value
        if (tvData != null) {
            val state = !tvData.fav
            watchUseCase.setTvFavorite(tvData, state)
        }
    }

}