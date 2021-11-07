package com.ort.firewolf.api.view.message

import com.ort.firewolf.domain.model.charachip.Charas
import com.ort.firewolf.domain.model.message.Messages
import com.ort.firewolf.domain.model.player.Players
import com.ort.firewolf.domain.model.village.Village

data class MessagesView(
    val list: List<MessageView>,
    val allRecordCount: Int?,
    val allPageCount: Int?,
    val existPrePage: Boolean?,
    val existNextPage: Boolean?,
    val currentPageNum: Int?,
    val todayMessageCountMap: Map<Int, Int>
) {
    constructor(
        messages: Messages,
        village: Village,
        players: Players,
        charas: Charas,
        todayMessages: Messages
    ) : this(
        list = messages.list.map {
            MessageView(
                message = it,
                village = village,
                players = players,
                charas = charas,
                shouldHidePlayer = !village.status.isSolved()
            )
        },
        allRecordCount = messages.allRecordCount,
        allPageCount = messages.allPageCount,
        existPrePage = messages.isExistPrePage,
        existNextPage = messages.isExistNextPage,
        currentPageNum = messages.currentPageNum,
        todayMessageCountMap = convertToMessageCountMap(village, todayMessages)
    )

    companion object {
        private fun convertToMessageCountMap(village: Village, todayMessages: Messages): Map<Int, Int> {
            return village.participant.memberList.map { member ->
                member.id to todayMessages.list.count { it.fromVillageParticipantId == member.id }
            }.toMap()
        }
    }
}

