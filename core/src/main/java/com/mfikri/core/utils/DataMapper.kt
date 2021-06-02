package com.mfikri.core.utils

import com.mfikri.core.data.source.remote.response.ResponseMoviesDetail
import com.mfikri.core.data.source.remote.response.ResponseTvDetail
import com.mfikri.core.domain.model.Movies
import com.mfikri.core.domain.model.Tv

object DataMapper {
    fun mapMovResponseToEntities(input: List<ResponseMoviesDetail>): List<com.mfikri.core.data.source.local.entity.EntityMovies> {
        val moviesList = ArrayList<com.mfikri.core.data.source.local.entity.EntityMovies>()
        input.map {
            val movies = com.mfikri.core.data.source.local.entity.EntityMovies(
                id = it.id,
                title = it.title,
                poster_path = it.poster_path,
                release_date = it.release_date,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                fav = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapMovEntitiesToDomain(input: List<com.mfikri.core.data.source.local.entity.EntityMovies>): List<Movies> {
        return input.map {
            Movies(
                id = it.id,
                title = it.title,
                poster_path = it.poster_path,
                release_date = it.release_date,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                fav = it.fav
            )
        }
    }

    fun mapMovEntityToDomain(input: com.mfikri.core.data.source.local.entity.EntityMovies): Movies {
        return Movies(
            input.id,
            input.title,
            input.poster_path,
            input.release_date,
            input.overview,
            input.vote_average,
            input.vote_count,
            input.fav
        )

    }

    fun mapMovDomainToEntity(input: Movies) = com.mfikri.core.data.source.local.entity.EntityMovies(
        id = input.id,
        title = input.title,
        poster_path = input.poster_path,
        release_date = input.release_date,
        overview = input.overview,
        vote_average = input.vote_average,
        vote_count = input.vote_count,
        fav = input.fav
    )

    fun mapTvResponseToEntities(input: List<ResponseTvDetail>): List<com.mfikri.core.data.source.local.entity.EntityTv> {
        val moviesList = ArrayList<com.mfikri.core.data.source.local.entity.EntityTv>()
        input.map {
            val tv = com.mfikri.core.data.source.local.entity.EntityTv(
                id = it.id,
                name = it.name,
                poster_path = it.poster_path,
                first_air_date = it.first_air_date,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                fav = false
            )
            moviesList.add(tv)
        }
        return moviesList
    }

    fun mapTvEntitiesToDomain(input: List<com.mfikri.core.data.source.local.entity.EntityTv>): List<Tv> =
        input.map {
            Tv(
                id = it.id,
                name = it.name,
                poster_path = it.poster_path,
                first_air_date = it.first_air_date,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                fav = it.fav
            )
        }

    fun mapTvEntityToDomain(input: com.mfikri.core.data.source.local.entity.EntityTv): Tv {
        return Tv(
            input.id,
            input.name,
            input.poster_path,
            input.first_air_date,
            input.overview,
            input.vote_average,
            input.vote_count,
            input.fav
        )

    }

    fun mapTvDomainToEntity(input: Tv) = com.mfikri.core.data.source.local.entity.EntityTv(
        id = input.id,
        name = input.name,
        poster_path = input.poster_path,
        first_air_date = input.first_air_date,
        overview = input.overview,
        vote_average = input.vote_average,
        vote_count = input.vote_count,
        fav = input.fav
    )

}