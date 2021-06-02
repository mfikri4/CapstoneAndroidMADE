package com.mfikri.capstoneandroidexpert.favorite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfikri.capstoneandroidexpert.favorite.presentation.FavoriteViewModel
import com.mfikri.core.domain.usecase.WatchUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val watchUseCase: WatchUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(watchUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }

}