package com.ort.firewolf.api.view.village

import com.ort.firewolf.domain.model.camp.Camp
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.VillageDays
import com.ort.firewolf.domain.model.village.VillageStatus
import com.ort.firewolf.domain.model.village.participant.VillageParticipants

data class SimpleVillageView(
    val id: Int,
    val name: String,
    val creatorPlayerId: Int,
    val status: VillageStatus,
    val winCamp: Camp?,
    val setting: VillageSettingsView,
    val participant: VillageParticipants,
    val spectator: VillageParticipants,
    val day: VillageDays
) {
    constructor(
        village: Village
    ) : this(
        id = village.id,
        name = village.name,
        creatorPlayerId = village.creatorPlayerId,
        status = village.status,
        winCamp = village.winCamp,
        setting = VillageSettingsView(village.setting),
        participant = VillageParticipants(
            count = village.participant.count,
            memberList = listOf()
        ),
        spectator = village.spectator,
        day = village.day
    )
}