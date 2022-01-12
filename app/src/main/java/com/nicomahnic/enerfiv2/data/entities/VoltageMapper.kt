package com.nicomahnic.enerfiv2.data.entities

import com.nicomahnic.enerfiv2.model.Voltage
import com.nicomahnic.enerfiv2.utils.EntityMapper
import javax.inject.Inject

class VoltageMapper @Inject constructor():
    EntityMapper<VoltageEntity, Voltage> {
    override fun mapFromEntity(entity: VoltageEntity): Voltage {
        return Voltage(
            x = entity.x,
            y = entity.y
        )
    }

    override fun mapToEntity(domainModel: Voltage): VoltageEntity {
        return VoltageEntity(
            id = 0,
            x = domainModel.x,
            y = domainModel.y
        )
    }


}