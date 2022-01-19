package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.DevicesByEmailResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.GeneralResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.MeasureByEmailAndDumResponseNetworkEntity
import com.nicomahnic.enerfiv2.model.server.response.DevicesByEmailResponse
import com.nicomahnic.enerfiv2.model.server.response.GeneralResponse
import com.nicomahnic.enerfiv2.model.server.response.MeasureByEmailAndDumResponse
import com.nicomahnic.enerfiv2.utils.EntityMapper
import java.sql.Timestamp
import javax.inject.Inject

class GeneralResponseMapper @Inject constructor():
    EntityMapper<GeneralResponseNetworkEntity, GeneralResponse> {

    override fun mapFromEntity(entity: GeneralResponseNetworkEntity): GeneralResponse {
        return GeneralResponse(
            message = entity.message,
            responseCode = entity.responseCode
        )
    }

    override fun mapToEntity(domainModel: GeneralResponse): GeneralResponseNetworkEntity {
        return GeneralResponseNetworkEntity(
            message = domainModel.message,
            responseCode = domainModel.responseCode
        )
    }

}

class PostDevicesByEmailResponseMapper @Inject constructor():
    EntityMapper<DevicesByEmailResponseNetworkEntity, DevicesByEmailResponse> {

    override fun mapFromEntity(entity: DevicesByEmailResponseNetworkEntity): DevicesByEmailResponse {
        return DevicesByEmailResponse(
            name = entity.name,
            mac = entity.mac
        )
    }

    override fun mapToEntity(domainModel: DevicesByEmailResponse): DevicesByEmailResponseNetworkEntity {
        return DevicesByEmailResponseNetworkEntity(
            id = 0,
            enable = true,
            name = domainModel.name,
            mac = domainModel.mac
        )
    }

    fun mapFromEntityList(entities: List<DevicesByEmailResponseNetworkEntity>) : List<DevicesByEmailResponse> {
        return entities.map { t ->  mapFromEntity(t) }
    }

    fun mapToEntityList(entities: List<DevicesByEmailResponse>) : List<DevicesByEmailResponseNetworkEntity> {
        return entities.map { t ->  mapToEntity(t) }
    }

}

class MeasureByEmailAndDumResponseMapper @Inject constructor():
    EntityMapper<MeasureByEmailAndDumResponseNetworkEntity, MeasureByEmailAndDumResponse> {

    override fun mapFromEntity(entity: MeasureByEmailAndDumResponseNetworkEntity): MeasureByEmailAndDumResponse {
        return MeasureByEmailAndDumResponse(
            vrms = entity.vrms,
            irms = entity.irms,
            activePower = entity.activePower,
            powerFactor = entity.powerFactor,
            thd = entity.thd,
            cosPhi = entity.cosPhi,
            timeStamp = entity.timeStamp
        )
    }

    override fun mapToEntity(domainModel: MeasureByEmailAndDumResponse): MeasureByEmailAndDumResponseNetworkEntity {
        return MeasureByEmailAndDumResponseNetworkEntity(
            vrms = domainModel.vrms,
            irms = domainModel.irms,
            activePower = domainModel.activePower,
            powerFactor = domainModel.powerFactor,
            thd = domainModel.thd,
            cosPhi = domainModel.cosPhi,
            timeStamp = domainModel.timeStamp
        )
    }

    fun mapFromEntityList(entities: List<MeasureByEmailAndDumResponseNetworkEntity>) : List<MeasureByEmailAndDumResponse> {
        return entities.map { t ->  mapFromEntity(t) }
    }

    fun mapToEntityList(entities: List<MeasureByEmailAndDumResponse>) : List<MeasureByEmailAndDumResponseNetworkEntity> {
        return entities.map { t ->  mapToEntity(t) }
    }

}