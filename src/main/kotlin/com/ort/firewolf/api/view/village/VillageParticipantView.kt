package com.ort.firewolf.api.view.village

import com.ort.firewolf.api.view.charachip.CharaView
import com.ort.firewolf.api.view.dead.DeadView
import com.ort.firewolf.api.view.player.PlayerView
import com.ort.firewolf.domain.model.camp.Camp
import com.ort.firewolf.domain.model.charachip.Charas
import com.ort.firewolf.domain.model.player.Players
import com.ort.firewolf.domain.model.skill.Skill
import com.ort.firewolf.domain.model.skill.SkillRequest
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.participant.VillageParticipantName
import com.ort.firewolf.domain.model.village.participant.VillageParticipantNotificationCondition
import com.ort.firewolf.domain.model.village.participant.VillageParticipantStatus
import com.ort.firewolf.domain.model.village.participant.coming_out.ComingOuts

data class VillageParticipantView(
    val id: Int,
    val name: String,
    val charaName: VillageParticipantName,
    val chara: CharaView,
    val player: PlayerView?,
    val status: VillageParticipantStatus,
    val dead: DeadView?,
    val spectator: Boolean,
    val skill: Skill?,
    val skillRequest: SkillRequest?,
    val win: Boolean?,
    val camp: Camp?,
    val commingOuts: ComingOuts,
    val notification: VillageParticipantNotificationCondition?
) {
    constructor(
        village: Village,
        villageParticipantId: Int,
        players: Players,
        charas: Charas,
        shouldHidePlayer: Boolean,
        shouldHideNotificationSetting: Boolean = true
    ) : this(
        id = village.memberById(villageParticipantId).id,
        name = village.memberById(villageParticipantId).name(),
        charaName = village.memberById(villageParticipantId).charaName,
        chara = CharaView(charas.chara(village.memberById(villageParticipantId).charaId)),
        player = if (shouldHidePlayer || village.memberById(villageParticipantId).playerId == null) null
        else PlayerView(players.list.find { it.id == village.memberById(villageParticipantId).playerId }!!),
        status = if (shouldHidePlayer) VillageParticipantStatus() else village.memberById(villageParticipantId).status,
        dead = village.memberById(villageParticipantId).dead?.let { DeadView(it, shouldHidePlayer) },
        spectator = village.memberById(villageParticipantId).isSpectator,
        skill = if (shouldHidePlayer) null else village.memberById(villageParticipantId).skill,
        skillRequest = if (shouldHidePlayer) null else village.memberById(villageParticipantId).skillRequest,
        win = village.memberById(villageParticipantId).isWin,
        camp = if (shouldHidePlayer) null else village.memberById(villageParticipantId).camp,
        commingOuts = village.memberById(villageParticipantId).commigOuts,
        notification = if (shouldHideNotificationSetting) null else village.memberById(villageParticipantId).notification
    )
}