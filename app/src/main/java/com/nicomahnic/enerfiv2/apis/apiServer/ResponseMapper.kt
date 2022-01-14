package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceResponseNetworkEntity
import com.nicomahnic.enerfiv2.model.response.PostNewDeviceResponse
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject


class PostNewDeviceResponseMapper @Inject constructor():
    EntityMapper<PostNewDeviceResponseNetworkEntity, PostNewDeviceResponse> {

    override fun mapFromEntity(entity: PostNewDeviceResponseNetworkEntity): PostNewDeviceResponse {
        return PostNewDeviceResponse(
                responseCode = entity.responseCode,
                macAddress = entity.macAddress
        )
    }

    override fun mapToEntity(domainModel: PostNewDeviceResponse): PostNewDeviceResponseNetworkEntity {
        return PostNewDeviceResponseNetworkEntity(
                responseCode = domainModel.responseCode,
                macAddress = domainModel.macAddress
        )
    }

}