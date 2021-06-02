package com.mfikri.core.data.source.remote.api

import com.mfikri.core.data.source.remote.response.ResponseMovies
import com.mfikri.core.data.source.remote.response.ResponseMoviesDetail
import com.mfikri.core.data.source.remote.response.ResponseTv
import com.mfikri.core.data.source.remote.response.ResponseTvDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): ResponseMovies

    @GET("discover/tv")
    suspend fun getTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): ResponseTv

    @GET("movie/{movieId}")
    suspend fun getMoviesDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") api_Key: String,
        @Query("language") language: String
    ): ResponseMoviesDetail

    @GET("tv/{tvId}")
    suspend fun getTvDetail(
        @Path("tvId") tvId: Int,
        @Query("api_key") api_Key: String,
        @Query("language") language: String
    ): ResponseTvDetail

}
