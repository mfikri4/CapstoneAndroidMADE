package com.mfikri.core.data.source.local.room

import androidx.room.*
import com.mfikri.core.data.source.local.entity.EntityMovies
import com.mfikri.core.data.source.local.entity.EntityTv
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoWatch {

    @Query("SELECT * FROM entity_movies")
    fun getMovies(): Flow<List<EntityMovies>>

    @Query("SELECT * FROM entity_tv")
    fun getTv(): Flow<List<EntityTv>>

    @Query("SELECT * FROM entity_movies WHERE id = :id")
    fun getMoviesbyId(id: Int): Flow<EntityMovies>

    @Query("SELECT * FROM entity_tv WHERE id = :id")
    fun getTvbyId(id: Int): Flow<EntityTv>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<EntityMovies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(tv: List<EntityTv>)

    @Update
    fun updateMovie(movies: EntityMovies)

    @Update
    fun updateTv(tv: EntityTv)

    @Query("SELECT * FROM entity_movies WHERE fav = 1")
    fun getFavoriteMovies(): Flow<List<EntityMovies>>

    @Query("SELECT * FROM entity_tv WHERE fav = 1")
    fun getFavoriteTv(): Flow<List<EntityTv>>

}