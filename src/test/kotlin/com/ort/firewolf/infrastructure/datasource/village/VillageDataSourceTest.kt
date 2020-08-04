package com.ort.firewolf.infrastructure.datasource.village

import com.ort.dbflute.allcommon.CDef
import com.ort.dbflute.exbhv.MessageRestrictionBhv
import com.ort.dbflute.exbhv.VillageBhv
import com.ort.dbflute.exbhv.VillageDayBhv
import com.ort.dbflute.exbhv.VillagePlayerBhv
import com.ort.dbflute.exbhv.VillageSettingBhv
import com.ort.dbflute.exentity.VillagePlayer
import com.ort.firewolf.FirewolfTest
import com.ort.firewolf.domain.model.camp.Camp
import com.ort.firewolf.domain.model.message.MessageType
import com.ort.firewolf.domain.model.skill.Skill
import com.ort.firewolf.domain.model.skill.SkillRequest
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.VillageCharachipCreateResource
import com.ort.firewolf.domain.model.village.VillageCreateResource
import com.ort.firewolf.domain.model.village.VillageDay
import com.ort.firewolf.domain.model.village.VillageDays
import com.ort.firewolf.domain.model.village.VillageMessageRestrictCreateResource
import com.ort.firewolf.domain.model.village.VillageOrganizationCreateResource
import com.ort.firewolf.domain.model.village.VillageRuleCreateResource
import com.ort.firewolf.domain.model.village.VillageSettingCreateResource
import com.ort.firewolf.domain.model.village.VillageStatus
import com.ort.firewolf.domain.model.village.VillageTimeCreateResource
import com.ort.firewolf.domain.model.village.participant.VillageParticipant
import com.ort.firewolf.domain.model.village.participant.VillageParticipants
import com.ort.firewolf.domain.model.village.setting.PersonCapacity
import com.ort.firewolf.domain.model.village.setting.VillageCharachip
import com.ort.firewolf.domain.model.village.setting.VillageMessageRestrict
import com.ort.firewolf.domain.model.village.setting.VillageMessageRestricts
import com.ort.firewolf.domain.model.village.setting.VillageOrganizations
import com.ort.firewolf.domain.model.village.setting.VillagePassword
import com.ort.firewolf.domain.model.village.setting.VillageRules
import com.ort.firewolf.domain.model.village.setting.VillageSettings
import com.ort.firewolf.domain.model.village.setting.VillageTime
import com.ort.firewolf.dummy.DummyDomainModelCreator
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@RunWith(SpringRunner::class)
@SpringBootTest
@Transactional
class VillageDataSourceTest : FirewolfTest() {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Autowired
    lateinit var villageDataSource: VillageDataSource
    @Autowired
    lateinit var villageBhv: VillageBhv
    @Autowired
    lateinit var villagePlayerBhv: VillagePlayerBhv
    @Autowired
    lateinit var villageSettingBhv: VillageSettingBhv
    @Autowired
    lateinit var messageRestrictionBhv: MessageRestrictionBhv
    @Autowired
    lateinit var villageDayBhv: VillageDayBhv

    // ===================================================================================
    //                                                                                Test
    //                                                                           =========
    @Test
    fun test_registerVillage() {
        // ## Arrange ##
        val createPlayerId = 1
        val startDatetime = LocalDateTime.now().plusDays(2L).withNano(0)
        val interval = 24 * 60 * 60
        val organization = "狼狼狼狂占霊狩村村村村村村村村村"

        val paramVillage = Village.createForRegister(
            resource = VillageCreateResource(
                villageName = "aaa",
                createPlayerId = 1,
                setting = VillageSettingCreateResource(
                    time = VillageTimeCreateResource(
                        startDatetime = startDatetime,
                        silentHours = null
                    ),
                    organization = VillageOrganizationCreateResource(
                        organization = "狼狼狼狂占霊狩村村村村村村村村村"
                    ),
                    charachip = VillageCharachipCreateResource(
                        dummyCharaId = 1,
                        charachipId = 1
                    ),
                    rule = VillageRuleCreateResource(
                        isOpenVote = false,
                        isAvailableSkillRequest = true,
                        isAvailableSpectate = false,
                        isOpenSkillInGrave = false,
                        isVisibleGraveMessage = false,
                        isAvailableSuddenlyDeath = true,
                        isAvailableCommit = false,
                        isAvailableDummySkill = false,
                        restrictList = listOf(
                            VillageMessageRestrictCreateResource(
                                type = MessageType(CDef.MessageType.通常発言),
                                count = 20,
                                length = 200
                            ),
                            VillageMessageRestrictCreateResource(
                                type = MessageType(CDef.MessageType.人狼の囁き),
                                count = 40,
                                length = 200
                            ),
                            VillageMessageRestrictCreateResource(
                                type = MessageType(CDef.MessageType.独り言),
                                count = 100,
                                length = 200
                            ),
                            VillageMessageRestrictCreateResource(
                                type = MessageType(CDef.MessageType.死者の呻き),
                                count = 40,
                                length = 200
                            )
                        ),
                        joinPassword = null
                    )
                )
            )
        )

        // ## Act ##
        val registeredVillage = villageDataSource.registerVillage(paramVillage)
        val villageId = registeredVillage.id

        // ## Assert ##
        // village
        val village = villageBhv.selectByPK(villageId).get()
        assertThat(village.createPlayerId).isEqualTo(createPlayerId)
        assertThat(village.villageStatusCodeAsVillageStatus).isEqualTo(CDef.VillageStatus.プロローグ)
        assertThat(village.epilogueDay as Int?).isNull()
        assertThat(village.winCampCodeAsCamp).isNull()
        // village_day
        val villageDayList = villageDayBhv.selectList { it.query().setVillageId_Equal(villageId) }
        assertThat(villageDayList.size).isEqualTo(1)
        assertThat(villageDayList.first()).satisfies { firstDay ->
            assertThat(firstDay.day).isEqualTo(0)
            assertThat(firstDay.noonnightCodeAsNoonnight).isEqualTo(CDef.Noonnight.昼)
            assertThat(firstDay.daychangeDatetime).isEqualTo(startDatetime)
        }
        // village_settings
        val settingList = villageSettingBhv.selectList { it.query().setVillageId_Equal(villageId) }
        assertThat(settingList).isNotEmpty
        assertThat(settingList.first { it.isVillageSettingItemCodeキャラクターグループid }.villageSettingText).isEqualTo("1")
        assertThat(settingList.first { it.isVillageSettingItemCodeコミット可能か }.villageSettingText).isEqualTo("0")
        assertThat(settingList.first { it.isVillageSettingItemCodeダミーキャラid }.villageSettingText).isEqualTo("1")
        assertThat(settingList.first { it.isVillageSettingItemCode役職希望可能か }.villageSettingText).isEqualTo("1")
        assertThat(settingList.first { it.isVillageSettingItemCode更新間隔秒 }.villageSettingText).isEqualTo(interval.toString())
        assertThat(settingList.first { it.isVillageSettingItemCode最低人数 }.villageSettingText).isEqualTo(organization.length.toString())
        assertThat(settingList.first { it.isVillageSettingItemCode最大人数 }.villageSettingText).isEqualTo(organization.length.toString())
        assertThat(settingList.first { it.isVillageSettingItemCode突然死ありか }.villageSettingText).isEqualTo("1")
        assertThat(settingList.first { it.isVillageSettingItemCode記名投票か }.villageSettingText).isEqualTo("0")
        assertThat(settingList.first { it.isVillageSettingItemCode開始予定日時 }.villageSettingText).isNotNull()
        assertThat(settingList.first { it.isVillageSettingItemCode期間形式 }.villageSettingText).isEqualTo(CDef.Term.長期.code())
        // message_restriction
        val restrictList = messageRestrictionBhv.selectList { it.query().setVillageId_Equal(villageId) }
        assertThat(restrictList.size).isEqualTo(4)
        assertThat(restrictList.first { it.isMessageTypeCode人狼の囁き }).satisfies { restrict ->
            assertThat(restrict.messageMaxLength).isEqualTo(200)
            assertThat(restrict.messageMaxNum).isEqualTo(40)
        }
        assertThat(restrictList.first { it.isMessageTypeCode通常発言 }).satisfies { restrict ->
            assertThat(restrict.messageMaxLength).isEqualTo(200)
            assertThat(restrict.messageMaxNum).isEqualTo(20)
        }
        assertThat(restrictList.first { it.isMessageTypeCode死者の呻き }).satisfies { restrict ->
            assertThat(restrict.messageMaxLength).isEqualTo(200)
            assertThat(restrict.messageMaxNum).isEqualTo(40)
        }
        assertThat(restrictList.first { it.isMessageTypeCode独り言 }).satisfies { restrict ->
            assertThat(restrict.messageMaxLength).isEqualTo(200)
            assertThat(restrict.messageMaxNum).isEqualTo(100)
        }
    }

    @Test
    fun test_findVillages() {
        // ## Arrange ##
        val village1 = villageDataSource.registerVillage(DummyDomainModelCreator.createDummyVillageRegisterParam())
        insertVillagePlayer(village1.id, createDummyVillageParticipantParam())
        insertVillagePlayer(village1.id, createDummyVillageParticipantParam())
        insertVillagePlayer(village1.id, createDummyVillageParticipantParam())
        val village2 = villageDataSource.registerVillage(DummyDomainModelCreator.createDummyVillageRegisterParam())
        insertVillagePlayer(village2.id, createDummyVillageParticipantParam())
        insertVillagePlayer(village2.id, createDummyVillageParticipantParam())
        insertVillagePlayer(village2.id, createDummyVillageParticipantParam())
        val village3 = villageDataSource.registerVillage(DummyDomainModelCreator.createDummyVillageRegisterParam())
        insertVillagePlayer(village3.id, createDummyVillageParticipantParam())
        insertVillagePlayer(village3.id, createDummyVillageParticipantParam())
        insertVillagePlayer(village3.id, createDummyVillageParticipantParam())

        // ## Act ##
        val villages = villageDataSource.findVillages()

        // ## Assert ##
        assertThat(villages.list.size).isEqualTo(3)
        assertThat(villages.list.first().participant.count).isEqualTo(3)
    }

    @Test
    fun test_findVillage() {
        // ## Arrange ##
        val param = DummyDomainModelCreator.createDummyVillageRegisterParam()

        // ## Act ##
        var village = villageDataSource.registerVillage(param)
        insertVillagePlayer(village.id, createDummyVillageParticipantParam())
        insertVillagePlayer(village.id, createDummyVillageParticipantParam())
        insertVillagePlayer(village.id, createDummyVillageParticipantParam())
        village = villageDataSource.findVillage(village.id)

        // ## Assert ##
        assertThat(village.creatorPlayerId).isEqualTo(param.creatorPlayerId)
        assertThat(village.status.toCdef()).isEqualTo(param.status.toCdef())
        assertThat(village.winCamp).isNull()
        assertThat(village.setting).satisfies { settings ->
            assertThat(settings.capacity.min).isEqualTo(param.setting.capacity.min)
            assertThat(settings.capacity.max).isEqualTo(param.setting.capacity.max)
            assertThat(settings.charachip.charachipId).isEqualTo(param.setting.charachip.charachipId)
            assertThat(settings.charachip.dummyCharaId).isEqualTo(param.setting.charachip.dummyCharaId)
            assertThat(settings.time.termType).isEqualTo(param.setting.time.termType)
            assertThat(settings.time.dayChangeIntervalSeconds).isEqualTo(param.setting.time.dayChangeIntervalSeconds)
            assertThat(settings.time.startDatetime.truncatedTo(ChronoUnit.SECONDS)).isEqualTo(
                param.setting.time.startDatetime.truncatedTo(
                    ChronoUnit.SECONDS
                )
            )
            assertThat(settings.organizations.organization.toString()).isEqualTo(param.setting.organizations.organization.toString())
            assertThat(settings.rules).satisfies { rules ->
                assertThat(rules.availableCommit).isEqualTo(param.setting.rules.availableCommit)
                assertThat(rules.availableSkillRequest).isEqualTo(param.setting.rules.availableSkillRequest)
                assertThat(rules.availableSpectate).isEqualTo(param.setting.rules.availableSpectate)
                assertThat(rules.availableSuddenlyDeath).isEqualTo(param.setting.rules.availableSuddenlyDeath)
                assertThat(rules.openSkillInGrave).isEqualTo(param.setting.rules.openSkillInGrave)
                assertThat(rules.openVote).isEqualTo(param.setting.rules.openVote)
                assertThat(rules.visibleGraveMessage).isEqualTo(param.setting.rules.visibleGraveMessage)
                assertThat(rules.messageRestrict.restrictList.size).isEqualTo(param.setting.rules.messageRestrict.restrictList.size)
            }
            assertThat(settings.password.joinPassword).isEqualTo(param.setting.password.joinPassword)
        }
        assertThat(village.winCamp).isNull()
        assertThat(village.participant.count).isEqualTo(3)
        assertThat(village.spectator.count).isEqualTo(0)
        assertThat(village.day.dayList.size).isEqualTo(1)
    }

    @Test
    fun test_updateDifference() {
        // ## Arrange ##
        val before = DummyDomainModelCreator.createDummyVillageRegisterParam()
        val beforeVillage = villageDataSource.registerVillage(before)
        val after = createDummyUpdateVillageParam().copy(id = beforeVillage.id)

        // ## Act ##
        val village = villageDataSource.updateDifference(beforeVillage, after)

        // ## Assert ##
        assertThat(village.name).isEqualTo(after.name)
        assertThat(village.creatorPlayerId).isEqualTo(after.creatorPlayerId)
        assertThat(village.status.toCdef()).isEqualTo(after.status.toCdef())
        assertThat(village.winCamp?.toCdef()).isEqualTo(after.winCamp?.toCdef())
        assertThat(village.setting).satisfies { settings ->
            assertThat(settings.capacity.min).isEqualTo(after.setting.capacity.min)
            assertThat(settings.capacity.max).isEqualTo(after.setting.capacity.max)
            assertThat(settings.charachip.charachipId).isEqualTo(after.setting.charachip.charachipId)
            assertThat(settings.charachip.dummyCharaId).isEqualTo(after.setting.charachip.dummyCharaId)
            assertThat(settings.time.termType).isEqualTo(after.setting.time.termType)
            assertThat(settings.time.epilogueDay).isEqualTo(after.setting.time.epilogueDay)
            assertThat(settings.time.dayChangeIntervalSeconds).isEqualTo(after.setting.time.dayChangeIntervalSeconds)
            assertThat(settings.time.startDatetime).isEqualTo(after.setting.time.startDatetime)
            assertThat(settings.organizations.organization.toString()).isEqualTo(after.setting.organizations.organization.toString())
            assertThat(settings.rules).satisfies { rules ->
                assertThat(rules.availableCommit).isEqualTo(after.setting.rules.availableCommit)
                assertThat(rules.availableSkillRequest).isEqualTo(after.setting.rules.availableSkillRequest)
                assertThat(rules.availableSpectate).isEqualTo(after.setting.rules.availableSpectate)
                assertThat(rules.availableSuddenlyDeath).isEqualTo(after.setting.rules.availableSuddenlyDeath)
                assertThat(rules.openSkillInGrave).isEqualTo(after.setting.rules.openSkillInGrave)
                assertThat(rules.openVote).isEqualTo(after.setting.rules.openVote)
                assertThat(rules.visibleGraveMessage).isEqualTo(after.setting.rules.visibleGraveMessage)
                assertThat(rules.messageRestrict.restrictList.any { it.type.toCdef() == CDef.MessageType.通常発言 }).isTrue()
                assertThat(rules.messageRestrict.restrictList.none { it.type.toCdef() == CDef.MessageType.人狼の囁き }).isTrue()
                assertThat(rules.messageRestrict.restrictList.any { it.type.toCdef() == CDef.MessageType.独り言 }).isTrue()
            }
            assertThat(settings.password.joinPassword).isEqualTo(after.setting.password.joinPassword)
        }
        assertThat(village.winCamp?.toCdef()).isEqualTo(after.winCamp?.toCdef())
        assertThat(village.participant.count).isEqualTo(2)
        assertThat(village.spectator.count).isEqualTo(2)
        assertThat(village.day.dayList.size).isEqualTo(2)
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun createDummyUpdateVillageParam(): Village {
        return Village(
            id = 1,
            name = "updated_village_name",
            creatorPlayerId = 1,
            status = VillageStatus(CDef.VillageStatus.進行中),
            setting = VillageSettings(
                capacity = PersonCapacity(
                    min = 12,
                    max = 14
                ),
                time = VillageTime(
                    termType = CDef.Term.短期.code(),
                    prologueStartDatetime = LocalDateTime.now(),
                    epilogueStartDatetime = LocalDateTime.now().plusDays(4L),
                    epilogueDay = 4,
                    startDatetime = LocalDateTime.now().plusDays(2L).withNano(0),
                    dayChangeIntervalSeconds = 48 * 60 * 60,
                    silentHours = null
                ),
                charachip = VillageCharachip(
                    dummyCharaId = 1,
                    charachipId = 1
                ),
                organizations = VillageOrganizations(),
                rules = VillageRules(
                    openVote = true,
                    availableSkillRequest = false,
                    availableSpectate = true,
                    openSkillInGrave = true,
                    visibleGraveMessage = true,
                    availableSuddenlyDeath = false,
                    availableCommit = true,
                    messageRestrict = VillageMessageRestricts(
                        existRestricts = true,
                        restrictList = listOf(
                            VillageMessageRestrict(
                                type = MessageType(CDef.MessageType.通常発言),
                                count = 40,
                                length = 400,
                                line = 20
                            ),
                            VillageMessageRestrict(
                                type = MessageType(CDef.MessageType.独り言),
                                count = 30,
                                length = 300,
                                line = 20
                            )
                        )
                    )
                ),
                password = VillagePassword(
                    joinPasswordRequired = true,
                    joinPassword = "password"
                )
            ),
            participant = VillageParticipants(
                count = 2,
                memberList = listOf(
                    createDummyVillageParticipantParam(),
                    createDummyVillageParticipantParam().copy(
                        charaId = 2,
                        playerId = 2,
                        skillRequest = SkillRequest(Skill(CDef.Skill.狩人), Skill(CDef.Skill.霊能者))
                    )
                )
            ),
            spectator = VillageParticipants(
                count = 2,
                memberList = listOf(
                    createDummyVillageParticipantParam().copy(
                        charaId = 3,
                        playerId = 3,
                        isSpectator = true
                    ),
                    createDummyVillageParticipantParam().copy(
                        charaId = 4,
                        playerId = 4,
                        isSpectator = true
                    )
                )
            ),
            day = VillageDays(
                dayList = listOf(
                    VillageDay(
                        id = 1,
                        day = 0,
                        noonnight = CDef.Noonnight.昼.code(),
                        dayChangeDatetime = LocalDateTime.now()
                    ),
                    VillageDay(
                        id = 1,
                        day = 1,
                        noonnight = CDef.Noonnight.昼.code(),
                        dayChangeDatetime = LocalDateTime.now().plusDays(2L)
                    )
                )
            ),
            winCamp = Camp(CDef.Camp.人狼陣営)
        )
    }

    private fun createDummyVillageParticipantParam(): VillageParticipant {
        return VillageParticipant(
            id = 1, // dummy
            charaId = 1,
            playerId = 1,
            dead = null,
            isSpectator = false,
            isGone = false,
            skill = null,
            skillRequest = SkillRequest(Skill(CDef.Skill.おまかせ), Skill(CDef.Skill.おまかせ)),
            isWin = null
        )
    }

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
}