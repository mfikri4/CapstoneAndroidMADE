package com.mfikri.core.domain.usecase

import com.mfikri.core.data.Resource
import com.mfikri.core.domain.model.Movies
import com.mfikri.core.domain.model.Tv
import com.mfikri.core.domain.repository.IWatchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WatchInteractor @Inject constructor(private val watchRepository: IWatchRepository) :
    WatchUseCase {
    override fun getMovie(): Flow<Resource<List<Movies>>> = watchRepository.getMovie()
    override fun getMovieDetail(movieId: Int): Flow<Resource<Movies>> =
        watchRepository.getMovieDetail(movieId)

    override fun getMoviesFavorite(): Flow<List<Movies>> = watchRepository.getMoviesFavorite()
    override fun setMoviesFavorite(movies: Movies, fav: Boolean) =
        watchRepository.setMoviesFavorite(movies, fav)

    override fun getTv(): Flow<Resource<List<Tv>>> = watchRepository.getTv()
    override fun getTvDetail(tvId: Int): Flow<Resource<Tv>> = watchRepository.getTvDetail(tvId)
    override fun getTvFavorite(): Flow<List<Tv>> = watchRepository.getTvFavorite()
    override fun setTvFavorite(tv: Tv, fav: Boolean) = watchRepository.setTvFavorite(tv, fav)

}