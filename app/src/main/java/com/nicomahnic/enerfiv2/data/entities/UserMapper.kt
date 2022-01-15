package com.nicomahnic.enerfiv2.data.entities

import com.nicomahnic.enerfiv2.model.local.User
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject

class UserMapper @Inject constructor():
    EntityMapper<UserEntity, User> {
    override fun mapFromEntity(entity: UserEntity): User {
        return User(
//            password = entity.password,
            mail = entity.mail

        )
    }

    override fun mapToEntity(domainModel: User): UserEntity {
        return UserEntity(
//            id = 0,
//            password = domainModel.password,
            mail = domainModel.mail
        )
    }


}