package com.ort.firewolf.domain.service.message

import com.ort.dbflute.allcommon.CDef
import com.ort.firewolf.domain.model.message.MessageQuery
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.participant.VillageParticipant
import com.ort.firewolf.domain.service.say.GraveSayDomainService
import com.ort.firewolf.domain.service.say.MonologueSayDomainService
import com.ort.firewolf.domain.service.say.NormalSayDomainService
import com.ort.firewolf.domain.service.say.SecretSayDomainService
import com.ort.firewolf.domain.service.say.SpectateSayDomainService
import com.ort.firewolf.domain.service.say.SympathizeSayDomainService
import com.ort.firewolf.domain.service.say.WerewolfSayDomainService
import org.springframework.stereotype.Service

@Service
class MessageDomainService(
    private val normalSayDomainService: NormalSayDomainService,
    private val werewolfSayDomainService: WerewolfSayDomainService,
    private val sympathizeSayDomainService: SympathizeSayDomainService,
    private val graveSayDomainService: GraveSayDomainService,
    private val spectateSayDomainService: SpectateSayDomainService,
    private val monologueSayDomainService: MonologueSayDomainService,
    private val secretSayDomainService: SecretSayDomainService,
    private val psychicMessageDomainService: PsychicMessageDomainService,
    private val guruPsychicMessageDomainService: GuruPsychicMessageDomainService,
    private val attackMessageDomainService: AttackMessageDomainService,
    private val autopsyMessageDomainService: AutopsyMessageDomainService,
    private val masonMessageDomainService: MasonMessageDomainService,
    private val sympathizerMessageDomainService: SympathizerMessageDomainService,
    private val fanaticMessageDomainService: FanaticMessageDomainService
) {

    private val everyoneAllowedMessageTypeList = listOf(CDef.MessageType.公開システムメッセージ, CDef.MessageType.通常発言, CDef.MessageType.村建て発言)

    /**
     * 閲覧できる発言種別リスト
     *
     * @param participant 参加情報
     * @param day 何日目か
     * @param authority ユーザの権限
     * @return 閲覧できる発言種別
     */
    fun viewableMessageTypeList(
        village: Village,
        participant: VillageParticipant?,
        day: Int,
        authority: CDef.Authority?
    ): List<CDef.MessageType> {
        // 管理者は全て見られる
        if (authority == CDef.Authority.管理者) return CDef.MessageType.listAll()
        // 村が終了していたら全て見られる
        if (village.status.isSolved()) return CDef.MessageType.listAll()

        val allowedTypeList: MutableList<CDef.MessageType> = mutableListOf()
        allowedTypeList.addAll(everyoneAllowedMessageTypeList)
        // 権限に応じて追加していく（独り言と秘話はここでは追加しない）
        listOf(
            CDef.MessageType.死者の呻き,
            CDef.MessageType.見学発言,
            CDef.MessageType.人狼の囁き,
            CDef.MessageType.共鳴発言,
            CDef.MessageType.白黒霊視結果,
            CDef.MessageType.役職霊視結果,
            CDef.MessageType.襲撃結果,
            CDef.MessageType.共有相互確認メッセージ,
            CDef.MessageType.共鳴相互確認メッセージ,
            CDef.MessageType.狂信者人狼確認メッセージ,
            CDef.MessageType.検死結果
        ).forEach {
            if (isViewableMessage(village, participant, it.code(), day)) allowedTypeList.add(it)
        }
        return allowedTypeList
    }

    /**
     * 閲覧できるか
     *
     * @param participant 参加情報
     * @param messageType 発言種別
     * @param day 何日目か
     * @return 閲覧できるか
     */
    fun isViewableMessage(
        village: Village,
        participant: VillageParticipant?,
        messageType: String,
        day: Int = 1
    ): Boolean {
        return when (CDef.MessageType.codeOf(messageType) ?: return false) {
            CDef.MessageType.通常発言 -> normalSayDomainService.isViewable(village, participant)
            CDef.MessageType.人狼の囁き -> werewolfSayDomainService.isViewable(village, participant)
            CDef.MessageType.死者の呻き -> graveSayDomainService.isViewable(village, participant)
            CDef.MessageType.共鳴発言 -> sympathizeSayDomainService.isViewable(village, participant)
            CDef.MessageType.見学発言 -> spectateSayDomainService.isViewable(village, participant, day)
            CDef.MessageType.独り言 -> monologueSayDomainService.isViewable(village, participant)
            CDef.MessageType.秘話 -> secretSayDomainService.isViewable(village, participant)
            CDef.MessageType.白黒霊視結果 -> psychicMessageDomainService.isViewable(village, participant)
            CDef.MessageType.役職霊視結果 -> guruPsychicMessageDomainService.isViewable(village, participant)
            CDef.MessageType.襲撃結果 -> attackMessageDomainService.isViewable(village, participant)
            CDef.MessageType.共有相互確認メッセージ -> masonMessageDomainService.isViewable(village, participant)
            CDef.MessageType.共鳴相互確認メッセージ -> sympathizerMessageDomainService.isViewable(village, participant)
            CDef.MessageType.狂信者人狼確認メッセージ -> fanaticMessageDomainService.isViewable(village, participant)
            CDef.MessageType.検死結果 -> autopsyMessageDomainService.isViewable(village, participant)
            else -> return false
        }
    }

    fun createQuery(
        village: Village,
        participant: VillageParticipant?,
        day: Int,
        authority: CDef.Authority?,
        messageTypeList: List<CDef.MessageType>?,
        from: Long?,
        pageSize: Int?,
        pageNum: Int?,
        keyword: String?,
        participantIdList: List<Int>?
    ): MessageQuery {
        val availableMessageTypeList = viewableMessageTypeList(village, participant, day, authority)
        val requestMessageTypeList = if (messageTypeList.isNullOrEmpty()) CDef.MessageType.listAll() else messageTypeList
        val queryMessageTypeList = requestMessageTypeList.filter { availableMessageTypeList.contains(it) }
        return MessageQuery(
            from = from,
            pageSize = pageSize,
            pageNum = pageNum,
            keyword = if (keyword.isNullOrBlank()) null else keyword,
            participant = participant,
            messageTypeList = queryMessageTypeList,
            participantIdList = participantIdList,
            includeMonologue = isIncludeMonologue(participant, participantIdList, requestMessageTypeList, queryMessageTypeList),
            includeSecret = false, // TODO 秘話実装する際に実装
            includePrivateAbility = isIncludePrivateAbility(participant, requestMessageTypeList)
        )
    }

    private fun isIncludeMonologue(
        participant: VillageParticipant?,
        participantIdList: List<Int>?,
        requestMessageTypeList: List<CDef.MessageType>,
        queryMessageTypeList: List<CDef.MessageType>
    ): Boolean {
        // 既に取得対象になっていれば不要
        if (queryMessageTypeList.contains(CDef.MessageType.独り言)) return false
        // 自分が取得対象になっていなければ不要
        participant ?: return false
        if (participantIdList != null && !participantIdList.contains(participant.id)) return false
        // 求めていなければ不要
        if (!requestMessageTypeList.contains(CDef.MessageType.独り言)) return false

        return true
    }

    // 霊視や襲撃でなく、占いなどの個別のが対象
    private fun isIncludePrivateAbility(
        participant: VillageParticipant?,
        requestMessageTypeList: List<CDef.MessageType>
    ): Boolean {
        participant ?: return false
        return requestMessageTypeList.contains(CDef.MessageType.公開システムメッセージ)
    }
}