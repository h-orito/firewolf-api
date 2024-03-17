package com.ort.firewolf.domain.service.message.say

import com.ort.firewolf.domain.model.player.Player
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class NormalSayDomainService : SayTypeDomainService {

    override fun isViewable(
        village: Village,
        myself: VillageParticipant?,
        player: Player?,
        day: Int
    ): Boolean = true

    override fun isSayable(
        village: Village,
        myself: VillageParticipant?,
        player: Player?
    ): Boolean {
        myself ?: return false
        return myself.isSayableNormalSay(village.status.isEpilogue()) && village.isSayableNormalSay()
    }
}