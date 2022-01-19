package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewUserRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostUserAndDumRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostUserRequestNetworkEntity
import com.nicomahnic.enerfiv2.model.server.request.PostNewDeviceRequest
import com.nicomahnic.enerfiv2.model.server.request.PostNewUserRequest
import com.nicomahnic.enerfiv2.model.server.request.PostUserAndDumRequest
import com.nicomahnic.enerfiv2.model.server.request.PostUserRequest
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject

class PostNewDeviceRequestMapper @Inject constructor():
    EntityMapper<PostNewDeviceRequestNetworkEntity, PostNewDeviceRequest> {

    override fun mapFromEntity(entity: PostNewDeviceRequestNetworkEntity): PostNewDeviceRequest {
        return PostNewDeviceRequest(
            macAddress = entity.macAddress,
            deviceName = entity.deviceName,
            mail = entity.mail,
            passwd = entity.passwd
        )
    }

    override fun mapToEntity(domainModel: PostNewDeviceRequest): PostNewDeviceRequestNetworkEntity {
        return PostNewDeviceRequestNetworkEntity(
            macAddress = domainModel.macAddress,
            deviceName = domainModel.deviceName,
            mail = domainModel.mail,
            passwd = domainModel.passwd
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
            passwd = domainModel.passwd,
            lastname = ""
        )
    }

}

class PostUserRequestMapper @Inject constructor():
    EntityMapper<PostUserRequestNetworkEntity, PostUserRequest> {

    override fun mapFromEntity(entity: PostUserRequestNetworkEntity): PostUserRequest {
        return PostUserRequest(
            mail = entity.mail,
            passwd = entity.passwd
        )
    }

    override fun mapToEntity(domainModel: PostUserRequest): PostUserRequestNetworkEntity {
        return PostUserRequestNetworkEntity(
            mail = domainModel.mail,
            passwd = domainModel.passwd
        )
    }

}

class PostUserAndDumRequestMapper @Inject constructor():
    EntityMapper<PostUserAndDumRequestNetworkEntity, PostUserAndDumRequest> {

    override fun mapFromEntity(entity: PostUserAndDumRequestNetworkEntity): PostUserAndDumRequest {
        return PostUserAndDumRequest(
            mail = entity.mail,
            passwd = entity.passwd,
            mac = entity.mac
        )
    }

    override fun mapToEntity(domainModel: PostUserAndDumRequest): PostUserAndDumRequestNetworkEntity {
        return PostUserAndDumRequestNetworkEntity(
            mail = domainModel.mail,
            passwd = domainModel.passwd,
            mac = domainModel.mac
        )
    }

}