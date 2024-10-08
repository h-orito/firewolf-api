package com.ort.firewolf.domain.model.message

import com.ort.dbflute.allcommon.CDef
import com.ort.firewolf.domain.model.village.participant.VillageParticipant

data class MessageQuery(
    val from: Long?,
    val pageSize: Int?,
    val pageNum: Int?,
    val keyword: String?,
    val participant: VillageParticipant?,
    val messageTypeList: List<CDef.MessageType>,
    val fromParticipantIdList: List<Int>?,
    val toParticipantIdList: List<Int>?,
    val includeMonologue: Boolean,
    val includeSecret: Boolean,
    val includePrivateAbility: Boolean,
    val isLatest: Boolean
) {
    constructor(
        messageTypeList: List<CDef.MessageType>
    ) : this(
        from = null,
        pageSize = null,
        pageNum = null,
        keyword = null,
        participant = null,
        messageTypeList = messageTypeList,
        fromParticipantIdList = null,
        toParticipantIdList = null,
        includeSecret = false,
        includeMonologue = false,
        includePrivateAbility = false,
        isLatest = false
    )

    companion object {
        val personalPrivateAbilityList = listOf(
            CDef.MessageType.白黒占い結果,
            CDef.MessageType.役職占い結果,
            CDef.MessageType.能力行使メッセージ,
            CDef.MessageType.恋人メッセージ
        )
    }

    fun isPaging(): Boolean = pageSize != null || pageNum != null
}