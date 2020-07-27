package com.ort.firewolf.domain.model.myself.participant

import com.ort.firewolf.domain.model.ability.AbilityType
import com.ort.firewolf.domain.model.village.participant.VillageParticipant

data class VillageAbilitySituation(
    val type: AbilityType,
    val targetList: List<VillageParticipant>,
    val target: VillageParticipant?,
    val usable: Boolean,
    val isAvailableNoTarget: Boolean
)
