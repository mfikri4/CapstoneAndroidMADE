package com.mfikri.capstoneandroidexpert.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfikri.core.data.Resource
import com.mfikri.core.domain.model.Movies
import com.mfikri.core.domain.model.Tv
import com.mfikri.core.domain.usecase.WatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FragViewModel @Inject constructor(private val watchUseCase: WatchUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movies>>> = watchUseCase.getMovie().asLiveData()
    fun getTv(): LiveData<Resource<List<Tv>>> = watchUseCase.getTv().asLiveData()

}