package com.ort.firewolf.api.view.player

import com.ort.firewolf.api.view.village.VillagesView
import com.ort.firewolf.domain.model.player.Player
import com.ort.firewolf.domain.model.village.Villages
import com.ort.firewolf.fw.security.FirewolfUser

data class MyselfPlayerView(
    val id: Int,
    val nickname: String,
    val twitterUserName: String?,
    val availableCreateVillage: Boolean,
    val participateProgressVillages: VillagesView,
    val participateFinishedVillages: VillagesView,
    val createProgressVillages: VillagesView,
    val createFinishedVillages: VillagesView
) {
    constructor(
        player: Player,
        participantVillages: Villages,
        createVillages: Villages,
        user: FirewolfUser
    ) : this(
        id = player.id,
        nickname = player.nickname,
        twitterUserName = player.twitterUserName,
        availableCreateVillage = player.isAvailableCreateVillage(user),
        participateProgressVillages = VillagesView(Villages(participantVillages.list.filter {
            !it.status.isSolved()
        })),
        participateFinishedVillages = VillagesView(Villages(participantVillages.list.filter {
            it.status.isSolved()
        })),
        createProgressVillages = VillagesView(Villages(createVillages.list.filter {
            !it.status.isSolved()
        })),
        createFinishedVillages = VillagesView(Villages(createVillages.list.filter {
            it.status.isSolved()
        }))
    )
}