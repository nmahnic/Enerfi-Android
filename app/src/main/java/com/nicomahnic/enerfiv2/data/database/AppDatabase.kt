package com.nicomahnic.enerfiv2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicomahnic.enerfiv2.data.entities.VoltageEntity
import com.nicomahnic.enerfiv2.domain.VoltageDao

@Database(entities = [VoltageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun voltageDao() : VoltageDao
}