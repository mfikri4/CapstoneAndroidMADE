package com.mfikri.core.data.source.remote

import android.util.Log
import com.mfikri.core.BuildConfig
import com.mfikri.core.data.source.remote.api.ApiResponse
import com.mfikri.core.data.source.remote.api.ApiService
import com.mfikri.core.data.source.remote.response.ResponseMoviesDetail
import com.mfikri.core.data.source.remote.response.ResponseTvDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSourceData @Inject constructor(private val apiService: ApiService) {
    fun getMovies(): Flow<ApiResponse<List<ResponseMoviesDetail>>> {
        return flow {
            try {
                val response = apiService.getMovies(
                    API_KEY,
                    LANGUAGE
                )
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteSourceData", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    fun getTv(): Flow<ApiResponse<List<ResponseTvDetail>>> {
        return flow {
            try {
                val response = apiService.getTv(
                    API_KEY,
                    LANGUAGE
                )
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteSourceData", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovie(movieId: Int): Flow<ApiResponse<ResponseMoviesDetail>> {
        return flow {
            try {
                val response = apiService.getMoviesDetail(
                    movieId,
                    API_KEY,
                    LANGUAGE
                )
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailTv(tvId: Int): Flow<ApiResponse<ResponseTvDetail>> {
        return flow {
            try {
                val response = apiService.getTvDetail(
                    tvId,
                    API_KEY,
                    LANGUAGE
                )
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val API_KEY = BuildConfig.TOKEN_TMDB
        private const val LANGUAGE = "en-US"
    }
}