package com.ort.firewolf.domain.service.coming_out

import com.ort.firewolf.domain.model.message.Message
import com.ort.firewolf.domain.model.myself.participant.VillageComingOutSituation
import com.ort.firewolf.domain.model.skill.Skill
import com.ort.firewolf.domain.model.skill.Skills
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.participant.VillageParticipant
import com.ort.firewolf.domain.model.village.participant.coming_out.ComingOuts
import com.ort.firewolf.fw.exception.FirewolfBusinessException
import org.springframework.stereotype.Service

@Service
class ComingOutDomainService {

    fun convertToSituation(village: Village, participant: VillageParticipant?): VillageComingOutSituation {
        return VillageComingOutSituation(
            availableComingOut = isAvailableComingOut(village, participant),
            currentComingOuts = participant?.commigOuts ?: ComingOuts(),
            selectableSkillList = selectableSkillList(village, participant)
        )
    }

    fun assertComingOut(
        village: Village,
        participant: VillageParticipant?
    ) {
        if (!isAvailableComingOut(village, participant)) throw FirewolfBusinessException("カミングアウトできません")
    }

    fun createComingOutMessage(myself: VillageParticipant, skills: Skills, villageDayId: Int): Message {
        return Message.createPublicSystemMessage(getComingOutSetMessage(myself, skills), villageDayId)
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun selectableSkillList(village: Village, participant: VillageParticipant?): List<Skill> {
        if (!isAvailableComingOut(village, participant)) return listOf()
        return village.setting.organizations.mapToSkillCount(village.participant.count)
            .filter { it.value > 0 }.keys.sortedBy { it.order().toInt() }.map { Skill(it) }
    }

    private fun isAvailableComingOut(village: Village, participant: VillageParticipant?): Boolean {
        // 村として可能か
        if (!village.isAvailableComingOut()) return false
        // 参加者として可能か
        participant ?: return false
        return participant.isAvailableComingOut()
    }

    private fun getComingOutSetMessage(myself: VillageParticipant, skills: Skills): String {
        return if (skills.list.isEmpty()) {
            "${myself.name()}がカミングアウトを取り消しました。"
        } else {
            val skillNames = skills.list.joinToString("と") { it.name }
            "${myself.name()}が${skillNames}をカミングアウトしました。"
        }
    }
}