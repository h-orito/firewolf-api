package com.ort.firewolf.infrastructure.datasource.reserved

import com.ort.dbflute.exbhv.ReservedVillageBhv
import com.ort.dbflute.exentity.ReservedVillage
import com.ort.firewolf.domain.model.reserved.ReservedVillages
import com.ort.firewolf.fw.FirewolfDateUtil
import org.springframework.stereotype.Repository

@Repository
class ReservedVillageDataSource(
    val reservedVillageBhv: ReservedVillageBhv
) {

    fun findReservedVillages(excludePast: Boolean = true): ReservedVillages {
        val reservedVillageList = reservedVillageBhv.selectList {
            if (excludePast) {
                it.query().setCreateDatetime_GreaterThan(FirewolfDateUtil.currentLocalDateTime())
            }
            it.query().addOrderBy_CreateDatetime_Asc()
            it.query().addOrderBy_StartDatetime_Asc()
        }
        return ReservedVillages(
            list = reservedVillageList.map { convertToReservedVillage(it) }
        )
    }

    fun findReservedVillage(id: Int): com.ort.firewolf.domain.model.reserved.ReservedVillage? {
        val optReservedVillage = reservedVillageBhv.selectByPK(id)
        return optReservedVillage.map { convertToReservedVillage(it) }.orElse(null)
    }

    fun registerReservedVillage(
        reservedVillage: com.ort.firewolf.domain.model.reserved.ReservedVillage
    ): com.ort.firewolf.domain.model.reserved.ReservedVillage {
        val entity = ReservedVillage()
        entity.createDatetime = reservedVillage.villageCreateDatetime
        entity.startDatetime = reservedVillage.villageStartDatetime
        entity.organization = reservedVillage.organization
        entity.silentHours = reservedVillage.silentHours
        entity.isAvailableDummySkill = reservedVillage.availableDummySkill
        reservedVillageBhv.insert(entity)
        return findReservedVillage(entity.reservedVillageId)!!
    }

    fun deleteReservedVillage(id: Int) {
        reservedVillageBhv.queryDelete { it.query().setReservedVillageId_Equal(id) }
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private fun convertToReservedVillage(reservedVillage: ReservedVillage): com.ort.firewolf.domain.model.reserved.ReservedVillage {
        return com.ort.firewolf.domain.model.reserved.ReservedVillage(
            id = reservedVillage.reservedVillageId,
            villageCreateDatetime = reservedVillage.createDatetime,
            villageStartDatetime = reservedVillage.startDatetime,
            organization = reservedVillage.organization,
            silentHours = reservedVillage.silentHours,
            availableDummySkill = reservedVillage.isAvailableDummySkill
        )
    }
}