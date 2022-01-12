package com.nicomahnic.enerfiv2.data.entities

import com.nicomahnic.enerfiv2.model.User
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject

class UserMapper @Inject constructor():
    EntityMapper<UserEntity, User> {
    override fun mapFromEntity(entity: UserEntity): User {
        return User(
            mail = entity.mail,
            password = entity.password
        )
    }

    override fun mapToEntity(domainModel: User): UserEntity {
        return UserEntity(
            id = 0,
            mail = domainModel.mail,
            password = domainModel.password
        )
    }


}