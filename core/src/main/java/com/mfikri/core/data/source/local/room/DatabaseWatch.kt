package com.mfikri.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mfikri.core.data.source.local.entity.EntityMovies
import com.mfikri.core.data.source.local.entity.EntityTv

@Database(
    entities = [EntityMovies::class, EntityTv::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseWatch : RoomDatabase() {
    abstract fun daoWatch(): DaoWatch
}