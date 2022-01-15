package com.nicomahnic.enerfiv2.repository.local

import android.util.Log
import com.nicomahnic.enerfiv2.data.entities.UserMapper
import com.nicomahnic.enerfiv2.domain.UserDao
import com.nicomahnic.enerfiv2.model.local.User
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class InsertUser @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper
) {

    suspend fun task(user: User) : Flow<DataState<String>> = flow {
        Log.d("NM", "insertUser llegue")
        try {
            userDao.insertUser(userMapper.mapToEntity(user))
            emit(DataState.Success("OK"))
        } catch (e: Exception){
            emit(DataState.Failure(e))
        }

    }

}