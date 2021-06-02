package com.mfikri.core.domain.usecase

import com.mfikri.core.data.Resource
import com.mfikri.core.domain.model.Movies
import com.mfikri.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface WatchUseCase {

    fun getMovie(): Flow<Resource<List<Movies>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<Movies>>
    fun getMoviesFavorite(): Flow<List<Movies>>
    fun setMoviesFavorite(movies: Movies, fav: Boolean)

    fun getTv(): Flow<Resource<List<Tv>>>
    fun getTvDetail(tvId: Int): Flow<Resource<Tv>>
    fun getTvFavorite(): Flow<List<Tv>>
    fun setTvFavorite(tv: Tv, fav: Boolean)
}