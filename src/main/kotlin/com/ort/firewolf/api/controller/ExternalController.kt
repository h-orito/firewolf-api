package com.ort.firewolf.api.controller

import com.ort.dbflute.allcommon.CDef
import com.ort.firewolf.api.form.VillageRecordListForm
import com.ort.firewolf.api.view.external.LatestVillageRecordView
import com.ort.firewolf.api.view.external.RecruitingVillagesView
import com.ort.firewolf.api.view.external.VillageRecordsView
import com.ort.firewolf.application.service.CharachipService
import com.ort.firewolf.application.service.PlayerService
import com.ort.firewolf.application.service.VillageService
import com.ort.firewolf.domain.model.village.VillageStatus
import com.ort.firewolf.domain.model.village.Villages
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExternalController(
    val villageService: VillageService,
    val charachipService: CharachipService,
    val playerService: PlayerService
) {

    @GetMapping("/recruiting-village-list")
    fun recruitingVillageList(): RecruitingVillagesView {
        val villageList = villageService.findVillages(
            villageStatusList = listOf(
                VillageStatus(CDef.VillageStatus.プロローグ),
                VillageStatus(CDef.VillageStatus.進行中),
                VillageStatus(CDef.VillageStatus.エピローグ)
            )
        ).list.sortedBy { it.id }

        val charachips = charachipService.findCharaChips()

        return RecruitingVillagesView(
            villageList = villageList,
            charachips = charachips
        )
    }

    @GetMapping("/village-record/list")
    fun villageRecordList(
        form: VillageRecordListForm
    ): VillageRecordsView {
        var villageIdList = villageService.findVillages(
            villageStatusList = listOf(
                VillageStatus(CDef.VillageStatus.廃村),
                VillageStatus(CDef.VillageStatus.エピローグ),
                VillageStatus(CDef.VillageStatus.終了)
            )
        ).list.map { it.id }
        form.vid?.let { vid -> villageIdList = villageIdList.filter { vid.contains(it) } }
        if (villageIdList.isEmpty()) return VillageRecordsView(listOf())
        val villageList = villageService.findVillagesAsDetail(villageIdList).list.sortedBy { it.id }
        val players = playerService.findPlayers(
            playerIdList = villageList.flatMap {
                (it.participant.memberList + it.spectator.memberList).map { member -> member.playerId!! }
            }.distinct()
        )
        return VillageRecordsView(
            villages = Villages(villageList),
            players = players
        )
    }

    @GetMapping("/village-record/latest-vid")
    fun latestvillageRecord(): LatestVillageRecordView {
        val maxVillageId = villageService.findVillages(
            villageStatusList = listOf(
                VillageStatus(CDef.VillageStatus.エピローグ),
                VillageStatus(CDef.VillageStatus.終了)
            )
        ).list.maxOfOrNull { it.id } ?: 0
        return LatestVillageRecordView(
            vid = maxVillageId
        )
    }
}