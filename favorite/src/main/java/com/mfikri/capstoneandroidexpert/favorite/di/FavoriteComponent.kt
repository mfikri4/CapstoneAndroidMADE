package com.mfikri.capstoneandroidexpert.favorite.di

import android.content.Context
import com.mfikri.capstoneandroidexpert.di.FavoriteModuleDependencies
import com.mfikri.capstoneandroidexpert.favorite.presentation.movies.MoviesFavFragment
import com.mfikri.capstoneandroidexpert.favorite.presentation.tv.TvFavFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun injectFav(fragmentMov: MoviesFavFragment)
    fun injectTv(fragmentTv: TvFavFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}