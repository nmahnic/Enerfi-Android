package com.nicomahnic.enerfiv2.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicomahnic.enerfiv2.data.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM userEntity")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM userEntity WHERE mail = :mail")
    suspend fun getUserByEmail(mail: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

}