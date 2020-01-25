package com.ort.wolf4busy.infrastructure.datasource.village

import com.ort.dbflute.allcommon.CDef
import com.ort.dbflute.exbhv.*
import com.ort.dbflute.exentity.*
import com.ort.wolf4busy.domain.model.village.Villages
import com.ort.wolf4busy.domain.model.village.participant.VillageParticipant
import com.ort.wolf4busy.domain.model.village.setting.VillageMessageRestrict
import com.ort.wolf4busy.domain.model.village.setting.VillageSettings
import com.ort.wolf4busy.fw.security.Wolf4busyUser
import com.ort.wolf4busy.infrastructure.datasource.village.converter.VillageDataConverter
import org.springframework.stereotype.Repository

@Repository
class VillageDataSource(
    val villageBhv: VillageBhv,
    val villageSettingBhv: VillageSettingBhv,
    val villageDayBhv: VillageDayBhv,
    val villagePlayerBhv: VillagePlayerBhv,
    val messageRestrictionBhv: MessageRestrictionBhv
) {

    /**
     * 村登録
     * @param paramVillage village
     * @return 村ID
     */
    fun registerVillage(
        paramVillage: com.ort.wolf4busy.domain.model.village.Village
    ): com.ort.wolf4busy.domain.model.village.Village {
        // 村
        val villageId = insertVillage(paramVillage)
        // 村設定
        insertVillageSettings(villageId, paramVillage.setting)
        // 発言制限
        insertMessageRestrictionList(villageId, paramVillage.setting)
        // 村日付
        insertVillageDay(
            villageId,
            com.ort.wolf4busy.domain.model.village.VillageDay(
                id = 1, // dummy
                day = 0,
                noonnight = CDef.Noonnight.昼.code(),
                dayChangeDatetime = paramVillage.setting.time.startDatetime
            )
        )

        return findVillage(villageId)
    }

    /**
     * 村一覧取得
     * @param user 指定した場合は自分が参加した村一覧
     * @return 村一覧
     */
    fun findVillages(user: Wolf4busyUser? = null): Villages {
        val villageList = villageBhv.selectList {
            it.specify().derivedVillagePlayer().count({ vpCB ->
                vpCB.specify().columnVillagePlayerId()
                vpCB.query().setIsGone_Equal(false)
                vpCB.query().setIsSpectator_Equal(false)
            }, Village.ALIAS_participantCount)
            it.specify().derivedVillagePlayer().count({ vpCB ->
                vpCB.specify().columnVillagePlayerId()
                vpCB.query().setIsGone_Equal(false)
                vpCB.query().setIsSpectator_Equal(true)
            }, Village.ALIAS_visitorCount)

            if (user != null) {
                it.query().existsVillagePlayer { vpCB ->
                    vpCB.query().setIsGone_Equal(false)
                    vpCB.query().queryPlayer().setUid_Equal(user.uid)
                }
            }

            it.query().addOrderBy_VillageId_Desc()
        }
        villageBhv.load(villageList) { loader ->
            loader.loadVillageSetting { }
            loader.loadVillageDay {
                it.query().addOrderBy_Day_Desc()
                it.query().queryNoonnight().addOrderBy_DispOrder_Desc()
            }
            loader.loadMessageRestriction { }
        }
        return VillageDataConverter.convertVillageListToVillages(villageList)
    }

    /**
     * 村一覧取得
     * @param villageIdList 村IDリスト
     * @return 村一覧
     */
    fun findVillages(villageIdList: List<Int>): Villages {
        val villageList = villageBhv.selectList {
            it.specify().derivedVillagePlayer().count({ vpCB ->
                vpCB.specify().columnVillagePlayerId()
                vpCB.query().setIsGone_Equal(false)
                vpCB.query().setIsSpectator_Equal(false)
            }, Village.ALIAS_participantCount)
            it.specify().derivedVillagePlayer().count({ vpCB ->
                vpCB.specify().columnVillagePlayerId()
                vpCB.query().setIsGone_Equal(false)
                vpCB.query().setIsSpectator_Equal(true)
            }, Village.ALIAS_visitorCount)

            it.query().setVillageId_InScope(villageIdList)
            it.query().addOrderBy_VillageId_Desc()
        }
        villageBhv.load(villageList) { loader ->
            loader.loadVillageSetting { }
            loader.loadVillageDay {
                it.query().addOrderBy_Day_Desc()
                it.query().queryNoonnight().addOrderBy_DispOrder_Desc()
            }
            loader.loadMessageRestriction { }
        }
        return VillageDataConverter.convertVillageListToVillages(villageList)
    }

    /**
     * 村情報取得
     * @param villageId villageId
     * @return 村情報
     */
    fun findVillage(villageId: Int, excludeGonePlayer: Boolean = true): com.ort.wolf4busy.domain.model.village.Village {
        val village = villageBhv.selectEntityWithDeletedCheck {
            it.query().setVillageId_Equal(villageId)
        }
        villageBhv.load(village) { loader ->
            loader.loadVillagePlayer { vpCB ->
                vpCB.setupSelect_VillageDay()
                if (excludeGonePlayer) {
                    vpCB.query().setIsGone_Equal(false)
                }
            }.withNestedReferrer {
                it.pulloutChara().loadCharaImage { }
            }
            loader.loadVillageSetting { }
            loader.loadVillageDay {
                it.query().addOrderBy_Day_Asc()
                it.query().queryNoonnight().addOrderBy_DispOrder_Asc()
            }
            loader.loadMessageRestriction { }
        }

        return VillageDataConverter.convertVillageToVillage(village)
    }


    /**
     * 差分更新
     * @param before village
     * @param after village
     */
    fun updateDifference(
        before: com.ort.wolf4busy.domain.model.village.Village,
        after: com.ort.wolf4busy.domain.model.village.Village
    ): com.ort.wolf4busy.domain.model.village.Village {
        // village
        updateVillageDifference(before, after)
        // village_day
        updateVillageDayDifference(before, after)
        // village_player
        updateVillagePlayerDifference(before, after)
        // village_setting
        updateVillageSettingDifference(before, after)
        // message_restriction
        updateMessageRestrictionDifference(before, after)

        return findVillage(before.id)
    }

    // ===================================================================================
    //                                                                              Update
    //                                                                              ======
    private fun updateVillageDifference(
        before: com.ort.wolf4busy.domain.model.village.Village,
        after: com.ort.wolf4busy.domain.model.village.Village
    ) {
        if (before.status.code != after.status.code || before.winCamp?.code != after.winCamp?.code) {
            updateVillage(after)
        }
    }

    private fun updateVillageDayDifference(
        before: com.ort.wolf4busy.domain.model.village.Village,
        after: com.ort.wolf4busy.domain.model.village.Village
    ) {
        if (!before.day.existsDifference(after.day)) return
        after.day.dayList
            .filterNot { afterDay -> before.day.dayList.any { it.id == afterDay.id } }
            .forEach {
                insertVillageDay(after.id, it)
            }
        after.day.dayList
            .filter { afterDay -> before.day.dayList.any { it.id == afterDay.id } }
            .forEach { afterDay ->
                val beforeDay = before.day.dayList.first { it.id == afterDay.id }
                if (afterDay.existsDifference(beforeDay)) updateVillageDay(afterDay)
            }
    }

    private fun updateVillagePlayerDifference(
        before: com.ort.wolf4busy.domain.model.village.Village,
        after: com.ort.wolf4busy.domain.model.village.Village
    ) {
        val villageId = after.id
        if (!before.participant.existsDifference(after.participant)) return
        // 新規
        after.participant.memberList.filterNot { member ->
            before.participant.memberList.any { it.id == member.id }
        }.forEach {
            insertVillagePlayer(villageId, it)
        }
        after.spectator.memberList.filterNot { member ->
            before.spectator.memberList.any { it.id == member.id }
        }.forEach {
            insertVillagePlayer(villageId, it)
        }

        // 更新
        after.participant.memberList.filter { member ->
            before.participant.memberList.any { it.id == member.id }
        }.forEach {
            val beforeMember = before.participant.member(it.id)
            if (it.existsDifference(beforeMember)) updateVillagePlayer(villageId, it)
        }
        after.spectator.memberList.filter { member ->
            before.spectator.memberList.any { it.id == member.id }
        }.forEach {
            val beforeMember = before.spectator.member(it.id)
            if (it.existsDifference(beforeMember)) updateVillagePlayer(villageId, it)
        }
    }

    private fun updateVillageSettingDifference(
        before: com.ort.wolf4busy.domain.model.village.Village,
        after: com.ort.wolf4busy.domain.model.village.Village
    ) {
        val villageId = after.id
        if (!before.setting.existsDifference(after.setting)) return

        after.setting.capacity.let(fun(afterCapacity) {
            if (!before.setting.capacity.existsDifference(afterCapacity)) return
            updateVillageSetting(villageId, CDef.VillageSettingItem.最低人数, afterCapacity.min.toString())
            updateVillageSetting(villageId, CDef.VillageSettingItem.最大人数, afterCapacity.max.toString())
        })
        after.setting.time.let(fun(afterTime) {
            if (!before.setting.time.existsDifference(afterTime)) return
            updateVillageSetting(villageId, CDef.VillageSettingItem.期間形式, afterTime.termType)
            updateVillageSetting(
                villageId,
                CDef.VillageSettingItem.開始予定日時,
                afterTime.startDatetime.format(VillageDataConverter.DATETIME_FORMATTER)
            )
            updateVillageSetting(villageId, CDef.VillageSettingItem.更新間隔秒, afterTime.dayChangeIntervalSeconds.toString())
        })
        after.setting.organizations.let(fun(afterOrg) {
            if (!before.setting.organizations.existsDifference(afterOrg)) return
            updateVillageSetting(villageId, CDef.VillageSettingItem.構成, afterOrg.toString())
        })
        after.setting.rules.let(fun(afterRules) {
            if (!before.setting.rules.existsDifference(afterRules)) return
            updateVillageSetting(villageId, CDef.VillageSettingItem.記名投票か, toFlg(afterRules.openVote))
            updateVillageSetting(villageId, CDef.VillageSettingItem.役職希望可能か, toFlg(afterRules.availableSkillRequest))
            updateVillageSetting(villageId, CDef.VillageSettingItem.見学可能か, toFlg(afterRules.availableSpectate))
            updateVillageSetting(villageId, CDef.VillageSettingItem.墓下役職公開ありか, toFlg(afterRules.openSkillInGrave))
            updateVillageSetting(villageId, CDef.VillageSettingItem.墓下見学発言を生存者が見られるか, toFlg(afterRules.visibleGraveMessage))
            updateVillageSetting(villageId, CDef.VillageSettingItem.突然死ありか, toFlg(afterRules.availableSuddenlyDeath))
            updateVillageSetting(villageId, CDef.VillageSettingItem.コミット可能か, toFlg(afterRules.availableCommit))
        })
        after.setting.password.let(fun(afterPassword) {
            if (!before.setting.password.existsDifference(afterPassword)) return
            updateVillageSetting(villageId, CDef.VillageSettingItem.入村パスワード, afterPassword.joinPassword ?: "")
        })
    }

    private fun updateMessageRestrictionDifference(
        before: com.ort.wolf4busy.domain.model.village.Village,
        after: com.ort.wolf4busy.domain.model.village.Village
    ) {
        val villageId = after.id
        val beforeRestricts = before.setting.rules.messageRestrict
        val afterRestricts = after.setting.rules.messageRestrict
        if (!beforeRestricts.existsDifference(afterRestricts)) return
        // 変更前にしかないものは削除
        beforeRestricts.restrictList.filterNot { beforeRestrict ->
            afterRestricts.restrictList.any { afterRestrict -> beforeRestrict.type.code == afterRestrict.type.code }
        }.forEach { deleteMessageRestriction(villageId, it) }
        // 両方にあるものは更新
        beforeRestricts.restrictList.filter { beforeRestrict ->
            afterRestricts.restrictList.any { afterRestrict -> beforeRestrict.type.code == afterRestrict.type.code }
        }.forEach { updateMessageRestriction(villageId, it) }
        // 変更後にしかないものは登録
        afterRestricts.restrictList.filterNot { afterRestrict ->
            beforeRestricts.restrictList.any { beforeRestrict -> beforeRestrict.type.code == afterRestrict.type.code }
        }.forEach { insertMessageRestriction(villageId, it) }
    }

    // ===================================================================================
    //                                                                             village
    //                                                                        ============
    /**
     * 村登録
     * @param villageModel 村
     * @return villageId
     */
    private fun insertVillage(villageModel: com.ort.wolf4busy.domain.model.village.Village): Int {
        val village = Village()
        village.villageDisplayName = villageModel.name
        village.villageStatusCodeAsVillageStatus = CDef.VillageStatus.codeOf(villageModel.status.code)
        village.createPlayerId = villageModel.creatorPlayerId
        villageBhv.insert(village)
        return village.villageId
    }

    /**
     * 村更新
     * @param villageModel 村
     */
    private fun updateVillage(villageModel: com.ort.wolf4busy.domain.model.village.Village) {
        val village = Village()
        village.villageId = villageModel.id
        village.villageDisplayName = villageModel.name
        village.villageStatusCodeAsVillageStatus = villageModel.status.toCdef()
        village.winCampCodeAsCamp = villageModel.winCamp?.toCdef()
        villageBhv.update(village)
    }

    // ===================================================================================
    //                                                                      village_player
    //                                                                        ============
    /**
     * 村参加者登録
     * @param villageId villageId
     * @param participant participant
     * @return 村参加ID
     */
    private fun insertVillagePlayer(
        villageId: Int,
        participant: VillageParticipant
    ): Int {
        val vp = VillagePlayer()
        vp.villageId = villageId
        vp.playerId = participant.playerId
        vp.charaId = participant.charaId
        vp.isDead = false
        vp.isSpectator = participant.isSpectator
        vp.isGone = false
        vp.requestSkillCodeAsSkill = participant.skillRequest.first.toCdef()
        vp.secondRequestSkillCodeAsSkill = participant.skillRequest.second.toCdef()
        villagePlayerBhv.insert(vp)
        return vp.villagePlayerId
    }

    private fun updateVillagePlayer(
        villageId: Int,
        villagePlayerModel: VillageParticipant
    ) {
        val villagePlayer = VillagePlayer()
        villagePlayer.villageId = villageId
        villagePlayer.villagePlayerId = villagePlayerModel.id
        villagePlayer.isDead = villagePlayerModel.dead != null
        villagePlayer.deadReasonCodeAsDeadReason = villagePlayerModel.dead?.toCdef()
        villagePlayer.deadVillageDayId = villagePlayerModel.dead?.villageDay?.id
        villagePlayer.isGone = villagePlayerModel.isGone
        villagePlayer.skillCodeAsSkill = villagePlayerModel.skill?.toCdef()
        villagePlayer.requestSkillCodeAsSkill = villagePlayerModel.skillRequest.first.toCdef()
        villagePlayer.secondRequestSkillCodeAsSkill = villagePlayerModel.skillRequest.second.toCdef()
        villagePlayerBhv.update(villagePlayer)
    }

    // ===================================================================================
    //                                                                         village_day
    //                                                                        ============
    /**
     * 村日付登録
     * @param villageId villageId
     * @param day 村日付
     * @return 村日付id
     */
    private fun insertVillageDay(
        villageId: Int,
        day: com.ort.wolf4busy.domain.model.village.VillageDay
    ): com.ort.wolf4busy.domain.model.village.VillageDay {
        val villageDay = VillageDay()
        villageDay.villageId = villageId
        villageDay.day = day.day
        villageDay.noonnightCodeAsNoonnight = CDef.Noonnight.codeOf(day.noonnight)
        villageDay.daychangeDatetime = day.dayChangeDatetime
        villageDayBhv.insert(villageDay)
        return VillageDataConverter.convertVillageDayToVillageDay(villageDay)
    }

    private fun updateVillageDay(
        day: com.ort.wolf4busy.domain.model.village.VillageDay
    ) {
        val villageDay = VillageDay()
        villageDay.villageDayId = day.id
        villageDay.daychangeDatetime = day.dayChangeDatetime
        villageDayBhv.update(villageDay)
    }

    // ===================================================================================
    //                                                                     village_setting
    //                                                                        ============
    /**
     * 村設定登録
     * @param villageId villageId
     * @param settings model settings
     */
    private fun insertVillageSettings(villageId: Int, settings: VillageSettings) {
        insertVillageSetting(villageId, CDef.VillageSettingItem.最低人数, settings.capacity.min.toString())
        insertVillageSetting(villageId, CDef.VillageSettingItem.最大人数, settings.capacity.max.toString())
        insertVillageSetting(villageId, CDef.VillageSettingItem.期間形式, settings.time.termType)
        insertVillageSetting(
            villageId,
            CDef.VillageSettingItem.開始予定日時,
            settings.time.startDatetime.format(VillageDataConverter.DATETIME_FORMATTER)
        )
        insertVillageSetting(villageId, CDef.VillageSettingItem.更新間隔秒, settings.time.dayChangeIntervalSeconds.toString())
        insertVillageSetting(villageId, CDef.VillageSettingItem.ダミーキャラid, settings.charachip.dummyCharaId.toString())
        insertVillageSetting(villageId, CDef.VillageSettingItem.キャラクターグループid, settings.charachip.charachipId.toString())
        insertVillageSetting(villageId, CDef.VillageSettingItem.構成, settings.organizations.toString())
        insertVillageSetting(villageId, CDef.VillageSettingItem.記名投票か, toFlg(settings.rules.openVote))
        insertVillageSetting(villageId, CDef.VillageSettingItem.役職希望可能か, toFlg(settings.rules.availableSkillRequest))
        insertVillageSetting(villageId, CDef.VillageSettingItem.見学可能か, toFlg(settings.rules.availableSpectate))
        insertVillageSetting(villageId, CDef.VillageSettingItem.墓下役職公開ありか, toFlg(settings.rules.openSkillInGrave))
        insertVillageSetting(villageId, CDef.VillageSettingItem.墓下見学発言を生存者が見られるか, toFlg(settings.rules.visibleGraveMessage))
        insertVillageSetting(villageId, CDef.VillageSettingItem.突然死ありか, toFlg(settings.rules.availableSuddenlyDeath))
        insertVillageSetting(villageId, CDef.VillageSettingItem.コミット可能か, toFlg(settings.rules.availableCommit))
        insertVillageSetting(villageId, CDef.VillageSettingItem.入村パスワード, settings.password.joinPassword ?: "")
    }

    private fun insertVillageSetting(villageId: Int, item: CDef.VillageSettingItem, value: String) {
        val setting = VillageSetting()
        setting.villageId = villageId
        setting.villageSettingItemCodeAsVillageSettingItem = item
        setting.villageSettingText = value
        villageSettingBhv.insert(setting)
    }

    private fun updateVillageSetting(villageId: Int, item: CDef.VillageSettingItem, value: String) {
        val setting = VillageSetting()
        setting.villageSettingText = value
        villageSettingBhv.queryUpdate(setting) {
            it.query().setVillageId_Equal(villageId)
            it.query().setVillageSettingItemCode_Equal_AsVillageSettingItem(item)
        }
    }

    // ===================================================================================
    //                                                                 message_restriction
    //                                                                        ============
    /**
     * 発言制限登録
     * @param villageId villageId
     * @param setting 村設定
     */
    private fun insertMessageRestrictionList(villageId: Int, setting: VillageSettings) {
        setting.rules.messageRestrict.restrictList.forEach {
            insertMessageRestriction(villageId, it.type.code, it.count, it.length)
        }
    }

    private fun insertMessageRestriction(villageId: Int, messageTypeCode: String, count: Int, length: Int) {
        val restrict = MessageRestriction()
        restrict.villageId = villageId
        restrict.messageTypeCodeAsMessageType = CDef.MessageType.codeOf(messageTypeCode)
        restrict.messageMaxNum = count
        restrict.messageMaxLength = length
        messageRestrictionBhv.insert(restrict)
    }

    private fun insertMessageRestriction(villageId: Int, restrict: VillageMessageRestrict) {
        insertMessageRestriction(villageId, restrict.type.code, restrict.count, restrict.length)
    }

    private fun updateMessageRestriction(villageId: Int, restrictModel: VillageMessageRestrict) {
        val restrict = MessageRestriction()
        restrict.messageMaxNum = restrictModel.count
        restrict.messageMaxLength = restrictModel.length
        messageRestrictionBhv.queryUpdate(restrict) {
            it.query().setVillageId_Equal(villageId)
            it.query().setMessageTypeCode_Equal_AsMessageType(restrictModel.type.toCdef())
        }
    }

    private fun deleteMessageRestriction(villageId: Int, restrict: VillageMessageRestrict) {
        messageRestrictionBhv.queryDelete {
            it.query().setVillageId_Equal(villageId)
            it.query().setMessageTypeCode_Equal_AsMessageType(restrict.type.toCdef())
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun toFlg(bool: Boolean): String {
        return if (bool) VillageDataConverter.FLG_TRUE else VillageDataConverter.FLG_FALSE
    }
}