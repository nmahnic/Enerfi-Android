package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceRequestNetworkEntity
import com.nicomahnic.enerfiv2.model.request.PostNewDeviceRequest
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
