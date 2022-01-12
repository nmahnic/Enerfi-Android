package com.nicomahnic.enerfiv2.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nicomahnic.enerfiv2.data.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM userEntity")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert
    suspend fun insertUser(user: UserEntity)

}