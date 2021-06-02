package com.mfikri.core.data.source.local

import com.mfikri.core.data.source.local.entity.EntityMovies
import com.mfikri.core.data.source.local.entity.EntityTv
import com.mfikri.core.data.source.local.room.DaoWatch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSourceData @Inject constructor(private val daoWatch: DaoWatch) {

    fun getMovies(): Flow<List<EntityMovies>> =
        daoWatch.getMovies()

    fun getTv(): Flow<List<EntityTv>> = daoWatch.getTv()

    fun getMoviebyId(id: Int): Flow<EntityMovies> =
        daoWatch.getMoviesbyId(id)

    fun getTvbyId(id: Int): Flow<EntityTv> =
        daoWatch.getTvbyId(id)

    suspend fun insertMovies(movies: List<EntityMovies>) =
        daoWatch.insertMovies(movies)

    suspend fun insertTv(tv: List<EntityTv>) =
        daoWatch.insertTv(tv)

    fun getMovieFavorite(): Flow<List<EntityMovies>> =
        daoWatch.getFavoriteMovies()

    fun setStatMovie(
        movies: EntityMovies,
        newState: Boolean
    ) {
        movies.fav = newState
        daoWatch.updateMovie(movies)
    }

    fun getTvFavorite(): Flow<List<EntityTv>> =
        daoWatch.getFavoriteTv()

    fun setStatTv(tv: EntityTv, newState: Boolean) {
        tv.fav = newState
        daoWatch.updateTv(tv)
    }

}