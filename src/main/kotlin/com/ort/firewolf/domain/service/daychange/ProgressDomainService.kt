package com.ort.firewolf.domain.service.daychange

import com.ort.firewolf.domain.model.charachip.Charas
import com.ort.firewolf.domain.model.commit.Commits
import com.ort.firewolf.domain.model.daychange.DayChange
import com.ort.firewolf.domain.model.message.Message
import com.ort.firewolf.domain.model.message.Messages
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.service.ability.AbilityDomainService
import com.ort.firewolf.domain.service.ability.AttackDomainService
import com.ort.firewolf.domain.service.ability.AutopsyDomainService
import com.ort.firewolf.domain.service.ability.BakeryDomainService
import com.ort.firewolf.domain.service.ability.DivineDomainService
import com.ort.firewolf.domain.service.ability.GuardDomainService
import com.ort.firewolf.domain.service.ability.GuruDomainService
import com.ort.firewolf.domain.service.ability.PsychicDomainService
import com.ort.firewolf.domain.service.ability.WandererGuardDomainService
import com.ort.firewolf.domain.service.ability.WiseDivineDomainService
import com.ort.firewolf.domain.service.vote.VoteDomainService
import com.ort.firewolf.fw.FirewolfDateUtil
import org.springframework.stereotype.Service

@Service
class ProgressDomainService(
    private val executeDomainService: ExecuteDomainService,
    private val psychicDomainService: PsychicDomainService,
    private val guruDomainService: GuruDomainService,
    private val divineDomainService: DivineDomainService,
    private val wiseDivineDomainService: WiseDivineDomainService,
    private val guardDomainService: GuardDomainService,
    private val wandererGuardDomainService: WandererGuardDomainService,
    private val attackDomainService: AttackDomainService,
    private val autopsyDomainService: AutopsyDomainService,
    private val bakeryDomainService: BakeryDomainService,
    private val miserableDeathDomainService: MiserableDeathDomainService,
    private val suddenlyDeathDomainService: SuddenlyDeathDomainService,
    private val epilogueDomainService: EpilogueDomainService,
    private val abilityDomainService: AbilityDomainService,
    private val voteDomainService: VoteDomainService
) {

    fun addDayIfNeeded(dayChange: DayChange, commits: Commits): DayChange {
        // 日付更新の必要がなかったら終了
        if (!shouldForward(dayChange.village, commits)) return dayChange
        // 日付追加
        return addNewDay(dayChange).setIsChange(dayChange)
    }

    fun dayChange(
        beforeDayChange: DayChange,
        todayMessages: Messages,
        charas: Charas
    ): DayChange {
        // 突然死
        var dayChange = suddenlyDeathDomainService.processDayChangeAction(beforeDayChange, todayMessages, charas)

        // 処刑
        dayChange = executeDomainService.processDayChangeAction(dayChange, charas)

        // 霊能
        dayChange = psychicDomainService.processDayChangeAction(dayChange, charas)

        // 導師
        dayChange = guruDomainService.processDayChangeAction(dayChange, charas)

        // 占い（呪殺、逆呪殺）
        dayChange = divineDomainService.processDayChangeAction(dayChange, charas)

        // 役職占い（呪殺、逆呪殺）
        dayChange = wiseDivineDomainService.processDayChangeAction(dayChange, charas)

        // 護衛
        dayChange = guardDomainService.processDayChangeAction(dayChange, charas)

        // 風来護衛
        dayChange = wandererGuardDomainService.processDayChangeAction(dayChange, charas)

        // 襲撃
        dayChange = attackDomainService.processDayChangeAction(dayChange, charas)

        // 無惨メッセージ
        dayChange = miserableDeathDomainService.processDayChangeAction(dayChange, charas)

        // 検死
        dayChange = autopsyDomainService.addAutopsyMessage(dayChange, charas)

        // 2日目限定メッセージ
        dayChange = addDay2MessageIfNeeded(dayChange)

        // 勝敗
        dayChange = epilogueDomainService.transitionToEpilogueIfNeeded(dayChange)

        // 勝敗が決していたらここで終了
        if (dayChange.village.status.isSolved()) return dayChange.setIsChange(beforeDayChange)

        // パン屋メッセージ登録
        dayChange = bakeryDomainService.addBakeryMessage(dayChange)

        // 投票や能力行使のデフォルト設定
        dayChange = abilityDomainService.addDefaultAbilities(dayChange)
        dayChange = voteDomainService.addDefaultVote(dayChange)

        // 生存者メッセージ登録
        dayChange = addAliveMemberMessage(dayChange, charas)

        return dayChange.setIsChange(beforeDayChange)
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    // 日付を進める必要があるか
    private fun shouldForward(village: Village, commits: Commits): Boolean {
        // 全員コミットしている
        if (allCommitted(village, commits)) return true
        // 更新日時を過ぎている
        return !FirewolfDateUtil.currentLocalDateTime().isBefore(village.day.latestDay().dayChangeDatetime)
    }

    private fun allCommitted(village: Village, commits: Commits): Boolean {
        if (!village.setting.rules.availableCommit) return false
        // ダミーを除く最新日の生存者数
        val livingPersonCount = village.notDummyParticipant().filterAlive().count
        // コミット数
        val commitCount = commits.list.filter { it.villageDayId == village.day.latestDay().id && it.isCommitting }.size

        return livingPersonCount == commitCount
    }

    // 日付追加
    private fun addNewDay(dayChange: DayChange): DayChange {
        return dayChange.copy(
            village = dayChange.village.addNewDay()
        ).setIsChange(dayChange)
    }

    private fun addDay2MessageIfNeeded(dayChange: DayChange): DayChange {
        val village = dayChange.village
        if (village.day.latestDay().day != 2) return dayChange
        return dayChange.copy(
            messages = dayChange.messages.add(village.createVillageDay2Message())
        )
    }

    // 生存者メッセージ
    private fun addAliveMemberMessage(dayChange: DayChange, charas: Charas): DayChange {
        return dayChange.copy(
            messages = dayChange.messages.add(createAliveMemberMessage(dayChange.village, charas))
        )
    }

    private fun createAliveMemberMessage(village: Village, charas: Charas): Message {
        val text = village.participant.filterAlive().memberList.joinToString(
            separator = "\n",
            prefix = "現在の生存者は以下の${village.participant.filterAlive().count}名。\n"
        ) { member ->
            charas.chara(member.charaId).charaName.fullName()
        }
        return Message.createPublicSystemMessage(text, village.day.latestDay().id)
    }
}