package com.nicomahnic.enerfiv2.repository

import android.util.Log
import com.nicomahnic.enerfiv2.data.entities.UserMapper
import com.nicomahnic.enerfiv2.domain.UserDao
import com.nicomahnic.enerfiv2.model.User
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class GetUsersByEmail @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper
) {

    suspend fun task(mail : String) : Flow<DataState<List<User>>> = flow {
        Log.d("NM", "getUser llegue")
        try {
            val res = mutableListOf<User>()
            val resEntity = userDao.getUserByEmail(mail)
            resEntity.forEach { res.add(userMapper.mapFromEntity(it)) }
            emit(DataState.Success(res))
        } catch (e: Exception){
            emit(DataState.Failure(e))
        }
    }

}