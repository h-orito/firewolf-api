package com.ort.wolf4busy.application.service

import com.ort.dbflute.allcommon.CDef
import com.ort.wolf4busy.domain.model.skill.Skill
import com.ort.wolf4busy.domain.model.skill.SkillRequest
import com.ort.wolf4busy.domain.model.village.Village
import com.ort.wolf4busy.domain.model.village.Villages
import com.ort.wolf4busy.domain.model.village.participant.VillageParticipant
import com.ort.wolf4busy.fw.security.Wolf4busyUser
import com.ort.wolf4busy.infrastructure.datasource.village.VillageDataSource
import org.springframework.stereotype.Service

@Service
class VillageService(
    val villageDataSource: VillageDataSource
) {

    /**
     * 村一覧取得
     * @return Villages
     */
    fun findVillageList(): Villages = villageDataSource.selectVillages()

    /**
     * 村取得
     * @param villageId villageId
     * @return Village
     */
    fun findVillage(villageId: Int): Village = villageDataSource.selectVillage(villageId)

    /**
     * 村登録
     * @param village village
     * @return villageId
     */
    fun registerVillage(village: Village, password: String?): Village {
        return villageDataSource.registerVillage(village, password)
    }

    /**
     * 村日付取得
     * @param villageId villageId
     * @param day 日付
     * @param noonnightCode 昼夜
     */
    fun findVillageDay(villageId: Int, day: Int, noonnightCode: String): com.ort.wolf4busy.domain.model.village.VillageDay =
        villageDataSource.selectVillageDay(villageId, day, noonnightCode)

    fun findVillageDayById(villageDayId: Int): com.ort.wolf4busy.domain.model.village.VillageDay =
        villageDataSource.selectVillageDayById(villageDayId)

    /**
     * 村日付を更新完了にする
     * @param villageDayId 村日付ID
     */
    fun updateVillageDayUpdateComplete(villageDayId: Int) = villageDataSource.updateVillageDayUpdateComplete(villageDayId)

    fun findParticipantByUid(villageId: Int, uid: String?): VillageParticipant? {
        uid ?: return null
        return villageDataSource.selectVillagePlayer(villageId, uid)
    }

    /**
     * 村参加者登録
     * @param villageId villageId
     * @param playerId playerId
     * @param charaId charaId
     * @param message 参加時発言
     * @param firstRequestSkill 役職第1希望
     * @param secondRequestSkill 役職第2希望
     * @param isSpectate 見学か
     * @return 村参加者ID
     */
    fun registerVillagePlayer(
        villageId: Int,
        playerId: Int,
        charaId: Int,
        message: String,
        firstRequestSkill: CDef.Skill = CDef.Skill.おまかせ,
        secondRequestSkill: CDef.Skill = CDef.Skill.おまかせ,
        isSpectate: Boolean = false
    ): Int {
        return villageDataSource.insertVillagePlayer(
            villageId,
            playerId,
            charaId,
            firstRequestSkill,
            secondRequestSkill,
            isSpectate
        )
    }

    /**
     * パスワードが正しいか
     *
     * @param villageId villageId
     * @param password 入村パスワード
     */
    fun isCollectPassword(villageId: Int, password: String?): Boolean {
        password ?: return false
        val villagePassword: String = villageDataSource.selectVillagePassword(villageId)
        return villagePassword == password
    }

    /**
     * どこかの村に参加しているか
     *
     * @param uid
     */
    fun isParticipatingAnyProgressVillage(uid: String): Boolean = villageDataSource.isParticipatingAnyProgressVillage(uid)

    /**
     * 役職希望を取得
     * @param
     * @return
     */
    fun findSkillRequest(participant: VillageParticipant?): SkillRequest? {
        participant ?: return null
        return villageDataSource.selectSkillRequest(participant)
    }

    /**
     * 役職希望変更
     * @param villageId villageId
     * @param user user
     * @param firstRequestSkill 第1希望
     * @param secondRequestSkill 第2希望
     */
    fun changeSkillRequest(villageId: Int, user: Wolf4busyUser, firstRequestSkill: String, secondRequestSkill: String) {
        val participant = this.findParticipantByUid(villageId, user.uid)
        checkNotNull(participant)
        val village = villageDataSource.selectVillage(villageId)
        if (!village.status.isPrologue()) return // 開始直前に変更しようとして間に合わなかった
        CDef.Skill.codeOf(firstRequestSkill) ?: IllegalStateException("改竄")
        CDef.Skill.codeOf(secondRequestSkill) ?: IllegalStateException("改竄")
        villageDataSource.updateSkillRequest(participant, Skill(firstRequestSkill, ""), Skill(secondRequestSkill, ""))
    }

    /**
     * 退村
     * @param participant 村参加者
     */
    fun leaveVillage(participant: VillageParticipant) = villageDataSource.updateVillagePlayerLeave(participant)

    /**
     * 差分更新
     *
     * @param before village
     * @param after village
     */
    fun updateVillageDifference(before: Village, after: Village) = villageDataSource.updateDifference(before, after)
}
