package com.ort.firewolf.api.view.debug

import com.ort.firewolf.api.view.player.PlayerView
import com.ort.firewolf.api.view.village.VillageParticipantsView
import com.ort.firewolf.api.view.village.VillageSettingsView
import com.ort.firewolf.domain.model.charachip.Charas
import com.ort.firewolf.domain.model.player.Player
import com.ort.firewolf.domain.model.player.Players
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.VillageDays
import com.ort.firewolf.domain.model.village.VillageStatus

data class DebugVillageView(
    val id: Int,
    val name: String,
    val creatorPlayer: PlayerView,
    val status: VillageStatus,
    val setting: VillageSettingsView,
    val participant: VillageParticipantsView,
    val spectator: VillageParticipantsView,
    val day: VillageDays
) {

    constructor(
        village: Village,
        charas: Charas,
        players: Players,
        createPlayer: Player
    ) : this(
        id = village.id,
        name = village.name,
        creatorPlayer = PlayerView(createPlayer),
        status = village.status,
        setting = VillageSettingsView(village.setting),
        participant = VillageParticipantsView(
            village = village,
            participants = village.participant,
            charas = charas,
            players = players,
            shouldHidePlayer = false
        ),
        spectator = VillageParticipantsView(
            village = village,
            participants = village.spectator,
            charas = charas,
            players = players,
            shouldHidePlayer = false
        ),
        day = village.day
    )
}