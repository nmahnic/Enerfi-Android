package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostGeneralResponseNetworkEntity
import com.nicomahnic.enerfiv2.model.server.response.PostGeneralResponse
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject


class PostNewDeviceResponseMapper @Inject constructor():
    EntityMapper<PostGeneralResponseNetworkEntity, PostGeneralResponse> {

    override fun mapFromEntity(entity: PostGeneralResponseNetworkEntity): PostGeneralResponse {
        return PostGeneralResponse(
            responseCode = entity.responseCode,
            message = entity.message
        )
    }

    override fun mapToEntity(domainModel: PostGeneralResponse): PostGeneralResponseNetworkEntity {
        return PostGeneralResponseNetworkEntity(
            responseCode = domainModel.responseCode,
            message = domainModel.message
        )
    }

}

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