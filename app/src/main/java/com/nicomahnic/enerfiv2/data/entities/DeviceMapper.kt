package com.nicomahnic.enerfiv2.data.entities

import com.nicomahnic.enerfiv2.model.local.Device
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject

class DeviceMapper @Inject constructor():
    EntityMapper<DeviceEntity, Device> {
    override fun mapFromEntity(entity: DeviceEntity): Device {
        return Device(
            mail = entity.mail,
            deviceName = entity.deviceName,
            mac = entity.mac,
            id = entity.id
        )
    }

    override fun mapToEntity(domainModel: Device): DeviceEntity {
        return DeviceEntity(
            mail = domainModel.mail,
            deviceName = domainModel.deviceName,
            mac = domainModel.mac,
            id = 0
        )
    }


}