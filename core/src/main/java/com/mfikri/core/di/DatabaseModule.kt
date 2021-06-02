package com.mfikri.core.di

import android.content.Context
import androidx.room.Room
import com.mfikri.core.data.source.local.room.DaoWatch
import com.mfikri.core.data.source.local.room.DatabaseWatch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DatabaseWatch {

        val passphrase: ByteArray = SQLiteDatabase.getBytes("CapstoneExpert".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            DatabaseWatch::class.java,
            "db_watch"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideWatchDao(databaseWatch: DatabaseWatch): DaoWatch {
        return databaseWatch.daoWatch()
    }

}