package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostDevicesByEmailResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostGeneralResponseNetworkEntity
import com.nicomahnic.enerfiv2.model.server.response.PostDevicesByEmailResponse
import com.nicomahnic.enerfiv2.model.server.response.PostGeneralResponse
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject

class PostGeneralResponseMapper @Inject constructor():
    EntityMapper<PostGeneralResponseNetworkEntity, PostGeneralResponse> {

    override fun mapFromEntity(entity: PostGeneralResponseNetworkEntity): PostGeneralResponse {
        return PostGeneralResponse(
            message = entity.message,
            responseCode = entity.responseCode
        )
    }

    override fun mapToEntity(domainModel: PostGeneralResponse): PostGeneralResponseNetworkEntity {
        return PostGeneralResponseNetworkEntity(
            message = domainModel.message,
            responseCode = domainModel.responseCode
        )
    }

}

class PostDevicesByEmailResponseMapper @Inject constructor():
    EntityMapper<PostDevicesByEmailResponseNetworkEntity, PostDevicesByEmailResponse> {

    override fun mapFromEntity(entity: PostDevicesByEmailResponseNetworkEntity): PostDevicesByEmailResponse {
        return PostDevicesByEmailResponse(
            name = entity.name,
            mac = entity.mac
        )
    }

    override fun mapToEntity(domainModel: PostDevicesByEmailResponse): PostDevicesByEmailResponseNetworkEntity {
        return PostDevicesByEmailResponseNetworkEntity(
            id = 0,
            enable = true,
            name = domainModel.name,
            mac = domainModel.mac
        )
    }

}