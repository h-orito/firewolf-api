package com.ort.firewolf.api.view.external

import com.ort.firewolf.domain.model.player.Players
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.Villages
import com.ort.firewolf.domain.model.village.participant.VillageParticipant
import java.time.format.DateTimeFormatter

data class VillageRecordsView(
    val list: List<VillageRecordView>
) {
    constructor(
        villages: Villages,
        players: Players
    ) : this(
        list = villages.list.map { VillageRecordView(it, players) }
    )
}

data class VillageRecordView(
    val id: Int,
    val name: String,
    val status: String,
    val organization: String,
    val startDatetime: String?,
    val prologueDatetime: String,
    val epilogueDatetime: String?,
    val epilogueDay: Int?,
    val url: String,
    val winCampName: String?,
    val participantList: List<VillageParticipantRecordView>
) {
    companion object {
        private val datetimePattern = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm")
    }

    constructor(
        village: Village,
        players: Players
    ) : this(
        id = village.id,
        name = village.name,
        status = village.status.name,
        organization = village.setting.organizations.organization[village.setting.capacity.max] ?: "",
        startDatetime = if (village.status.isCanceled()) null
        else village.setting.time.startDatetime.format(datetimePattern),
        prologueDatetime = village.setting.time.prologueStartDatetime.format(datetimePattern),
        epilogueDatetime = if (village.status.isCanceled()) null
        else village.setting.time.epilogueStartDatetime!!.format(datetimePattern),
        epilogueDay = if (village.status.isCanceled()) null
        else village.setting.time.epilogueDay!!,
        url = "https://firewolf.netlify.app/village?id=${village.id}",
        winCampName = if (village.status.isCanceled()) null
        else village.winCamp!!.name,
        participantList = (village.participant.memberList + village.spectator.memberList).map {
            VillageParticipantRecordView(it, players)
        }
    )
}

data class VillageParticipantRecordView(
    val twitterUserId: String?,
    val otherSiteUserId: String?,
    val characterName: String,
    val skillName: String?,
    val isSpectator: Boolean,
    val isWin: Boolean?,
    val isDead: Boolean,
    val deadDay: Int?,
    val deadReason: String?
) {
    constructor(
        participant: VillageParticipant,
        players: Players
    ) : this(
        twitterUserId =
        players.list.first { it.id == participant.playerId }.let {
            it.twitterUserName ?: it.nickname
        },
        otherSiteUserId = players.list.first { it.id == participant.playerId }.otherSiteName,
        characterName = participant.name(),
        skillName = participant.skill?.name,
        isSpectator = participant.isSpectator,
        isWin = participant.isWin,
        isDead = participant.dead != null,
        deadDay = participant.dead?.villageDay?.day,
        deadReason = participant.dead?.let { it.reason + "死" }
    )
}