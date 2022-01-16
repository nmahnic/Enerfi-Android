package com.nicomahnic.enerfiv2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nicomahnic.enerfiv2.data.entities.UserEntity
import com.nicomahnic.enerfiv2.data.entities.VoltageEntity
import com.nicomahnic.enerfiv2.domain.UserDao
import com.nicomahnic.enerfiv2.domain.VoltageDao

@Database(entities = [VoltageEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun voltageDao() : VoltageDao
    abstract fun userDao() : UserDao

}