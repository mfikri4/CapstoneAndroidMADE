package com.mfikri.capstoneandroidexpert.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfikri.core.domain.usecase.WatchUseCase

class FavoriteViewModel(private val watchUseCase: WatchUseCase) : ViewModel() {
    fun getMoviesFavorite() = watchUseCase.getMoviesFavorite().asLiveData()
    fun getTvFavorite() = watchUseCase.getTvFavorite().asLiveData()
}