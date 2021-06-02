package com.mfikri.core.data

import com.mfikri.core.data.source.local.LocalSourceData
import com.mfikri.core.data.source.local.entity.EntityMovies
import com.mfikri.core.data.source.local.entity.EntityTv
import com.mfikri.core.data.source.remote.RemoteSourceData
import com.mfikri.core.data.source.remote.api.ApiResponse
import com.mfikri.core.data.source.remote.response.ResponseMoviesDetail
import com.mfikri.core.data.source.remote.response.ResponseTvDetail
import com.mfikri.core.domain.model.Movies
import com.mfikri.core.domain.model.Tv
import com.mfikri.core.domain.repository.IWatchRepository
import com.mfikri.core.utils.DataMapper
import com.mfikri.core.utils.ExecutorsApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteSourceData: RemoteSourceData,
    private val localSourceData: LocalSourceData,
    private val executorsApp: ExecutorsApp
) : IWatchRepository {

    override fun getMovie(): Flow<Resource<List<Movies>>> {
        return object :
            ResourceNetworkBound<List<Movies>, List<ResponseMoviesDetail>>() {
            public override fun loadFromDB(): Flow<List<Movies>> {
                return localSourceData.getMovies().map {
                    DataMapper.mapMovEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResponseMoviesDetail>>> {
                return remoteSourceData.getMovies()
            }

            override suspend fun saveCallResult(data: List<ResponseMoviesDetail>) {
                val movies = DataMapper.mapMovResponseToEntities(data)
                localSourceData.insertMovies(movies)
            }
        }.asFlow()
    }

    override fun getTv(): Flow<Resource<List<Tv>>> {
        return object :
            ResourceNetworkBound<List<Tv>, List<ResponseTvDetail>>() {
            public override fun loadFromDB(): Flow<List<Tv>> {
                return localSourceData.getTv().map {
                    DataMapper.mapTvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tv>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResponseTvDetail>>> {
                return remoteSourceData.getTv()
            }

            override suspend fun saveCallResult(data: List<ResponseTvDetail>) {
                val tvShow = DataMapper.mapTvResponseToEntities(data)
                localSourceData.insertTv(tvShow)
            }
        }.asFlow()
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<Movies>> {
        return object : ResourceNetworkBound<Movies, ResponseMoviesDetail>() {
            override fun loadFromDB(): Flow<Movies> {
                return localSourceData.getMoviebyId(movieId)
                    .map { DataMapper.mapMovEntityToDomain(it) }
            }

            override fun shouldFetch(data: Movies?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<ResponseMoviesDetail>> {
                return remoteSourceData.getDetailMovie(movieId)
            }

            override suspend fun saveCallResult(data: ResponseMoviesDetail) {
                val movie = EntityMovies(
                    data.id,
                    data.title,
                    data.poster_path,
                    data.release_date,
                    data.overview,
                    data.vote_average,
                    data.vote_count
                )
                localSourceData.insertMovies(arrayListOf(movie))
            }
        }.asFlow()
    }

    override fun getTvDetail(tvId: Int): Flow<Resource<Tv>> {
        return object : ResourceNetworkBound<Tv, ResponseTvDetail>() {
            override fun loadFromDB(): Flow<Tv> {
                return localSourceData.getTvbyId(tvId).map { DataMapper.mapTvEntityToDomain(it) }
            }

            override fun shouldFetch(data: Tv?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<ResponseTvDetail>> {
                return remoteSourceData.getDetailTv(tvId)
            }

            override suspend fun saveCallResult(data: ResponseTvDetail) {
                val tv = EntityTv(
                    data.id,
                    data.name,
                    data.poster_path,
                    data.first_air_date,
                    data.overview,
                    data.vote_average,
                    data.vote_count
                )
                localSourceData.insertTv(arrayListOf(tv))
            }
        }.asFlow()
    }

    override fun getMoviesFavorite(): Flow<List<Movies>> {
        return localSourceData.getMovieFavorite().map { DataMapper.mapMovEntitiesToDomain(it) }
    }

    override fun setMoviesFavorite(movies: Movies, fav: Boolean) {
        val moviesEnt = DataMapper.mapMovDomainToEntity(movies)
        executorsApp.diskIO().execute { localSourceData.setStatMovie(moviesEnt, fav) }
    }

    override fun getTvFavorite(): Flow<List<Tv>> {
        return localSourceData.getTvFavorite().map { DataMapper.mapTvEntitiesToDomain(it) }
    }

    override fun setTvFavorite(tv: Tv, fav: Boolean) {
        val tvEnt = DataMapper.mapTvDomainToEntity(tv)
        executorsApp.diskIO().execute { localSourceData.setStatTv(tvEnt, fav) }
    }


}