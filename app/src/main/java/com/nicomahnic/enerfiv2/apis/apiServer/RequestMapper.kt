package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewUserRequestNetworkEntity
import com.nicomahnic.enerfiv2.model.server.request.PostNewDeviceRequest
import com.nicomahnic.enerfiv2.model.server.request.PostNewUserRequest
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject

class PostNewDeviceRequestMapper @Inject constructor():
    EntityMapper<PostNewDeviceRequestNetworkEntity, PostNewDeviceRequest> {

    override fun mapFromEntity(entity: PostNewDeviceRequestNetworkEntity): PostNewDeviceRequest {
        return PostNewDeviceRequest(
            macAddress = entity.macAddress
        )
    }

    override fun mapToEntity(domainModel: PostNewDeviceRequest): PostNewDeviceRequestNetworkEntity {
        return PostNewDeviceRequestNetworkEntity(
            macAddress = domainModel.macAddress
        )
    }
}

class PostNewUserRequestMapper @Inject constructor():
    EntityMapper<PostNewUserRequestNetworkEntity, PostNewUserRequest> {

    override fun mapFromEntity(entity: PostNewUserRequestNetworkEntity): PostNewUserRequest {
        return PostNewUserRequest(
            name = entity.name,
            mail = entity.mail,
            passwd = entity.passwd
        )
    }

    override fun mapToEntity(domainModel: PostNewUserRequest): PostNewUserRequestNetworkEntity {
        return PostNewUserRequestNetworkEntity(
            name = domainModel.name,
            mail = domainModel.mail,
            passwd = domainModel.passwd
        )
    }

}