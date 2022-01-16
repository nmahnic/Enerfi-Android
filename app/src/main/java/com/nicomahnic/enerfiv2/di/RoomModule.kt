package com.nicomahnic.enerfiv2.di

import android.content.Context
import androidx.room.Room
import com.nicomahnic.enerfiv2.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Enerfi.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideVoltageDao(db: AppDatabase) = db.voltageDao()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideDeviceDao(db: AppDatabase) = db.deviceDao()
}