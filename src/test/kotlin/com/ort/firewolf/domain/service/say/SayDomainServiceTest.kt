package com.ort.firewolf.domain.service.say

import com.ort.dbflute.allcommon.CDef
import com.ort.firewolf.FirewolfTest
import com.ort.firewolf.domain.model.charachip.CharaFace
import com.ort.firewolf.domain.model.dead.Dead
import com.ort.firewolf.domain.model.message.Message
import com.ort.firewolf.domain.model.message.MessageContent
import com.ort.firewolf.domain.model.message.MessageType
import com.ort.firewolf.domain.model.village.VillageDays
import com.ort.firewolf.domain.model.village.VillageStatus
import com.ort.firewolf.domain.model.village.setting.VillageMessageRestrict
import com.ort.firewolf.domain.model.village.setting.VillageMessageRestricts
import com.ort.firewolf.domain.service.message.say.SayDomainService
import com.ort.firewolf.dummy.DummyDomainModelCreator
import com.ort.firewolf.fw.exception.FirewolfBadRequestException
import com.ort.firewolf.fw.exception.FirewolfBusinessException
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
class SayDomainServiceTest : FirewolfTest() {

    @Autowired
    lateinit var sayDomainService: SayDomainService

    // ===================================================================================
    //                                                                                Test
    //                                                                           =========
    @Test(expected = FirewolfBusinessException::class)
    fun test_assertSay_参加していない() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage()
        val participant = null
        val chara = null
        val latestDayMessageList: List<Message> = listOf()
        val messageContent = DummyDomainModelCreator.createDummyMessageContent()

        // ## Act ##
        // ## Assert ##
        sayDomainService.assertSay(village, participant, chara, latestDayMessageList, messageContent)
    }

    @Test(expected = FirewolfBusinessException::class)
    fun test_assertSay_突然死_進行中() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中)
        )
        val participant = DummyDomainModelCreator.createDummyDeadWolf().copy(
            dead = Dead(CDef.DeadReason.突然, DummyDomainModelCreator.createDummyVillageDay())
        )
        val chara = null
        val latestDayMessageList: List<Message> = listOf()
        val messageContent = DummyDomainModelCreator.createDummyMessageContent()

        // ## Act ##
        // ## Assert ##
        sayDomainService.assertSay(village, participant, chara, latestDayMessageList, messageContent)
    }

    @Test(expected = FirewolfBusinessException::class)
    fun test_assertSay_廃村() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.廃村)
        )
        val participant = DummyDomainModelCreator.createDummyDeadWolf()
        val chara = null
        val latestDayMessageList: List<Message> = listOf()
        val messageContent = DummyDomainModelCreator.createDummyMessageContent()

        // ## Act ##
        // ## Assert ##
        sayDomainService.assertSay(village, participant, chara, latestDayMessageList, messageContent)
    }

    @Test
    fun test_assertSay_発言制限OK() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中),
            setting = DummyDomainModelCreator.createDummyVillageSettings().copy(
                rules = DummyDomainModelCreator.createDummyVillageRules().copy(
                    messageRestrict = VillageMessageRestricts()
                )
            ),
            day = VillageDays(
                listOf(
                    DummyDomainModelCreator.createDummyVillageDay().copy(
                        dayChangeDatetime = LocalDateTime.now().minusMinutes(30L)
                    ),
                    DummyDomainModelCreator.createDummyVillageDay()
                )
            )
        )
        val participant = DummyDomainModelCreator.createDummyAliveVillager()
        val chara = DummyDomainModelCreator.createDummyChara().copy(
            id = participant.charaId,
            faceList = listOf(
                CharaFace(CDef.FaceType.通常.code(), CDef.FaceType.通常.alias(), "url")
            )
        )
        val latestDayMesasgeList = (1..19).map {
            DummyDomainModelCreator.createDummyMessage().copy(
                content = DummyDomainModelCreator.createDummyMessageContent().copy(
                    type = MessageType(CDef.MessageType.通常発言)
                )
            )
        }
        val messageContent = MessageContent(
            type = MessageType(CDef.MessageType.通常発言),
            num = null,
            count = null,
            text = "hoge",
            faceCode = CDef.FaceType.通常.code()
        )

        // ## Act ##
        // ## Assert ##
        sayDomainService.assertSay(village, participant, chara, latestDayMesasgeList, messageContent)
    }

    @Test(expected = FirewolfBusinessException::class)
    fun test_assertSay_発言制限NG_回数() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中),
            setting = DummyDomainModelCreator.createDummyVillageSettings().copy(
                rules = DummyDomainModelCreator.createDummyVillageRules().copy(
                    messageRestrict = VillageMessageRestricts()
                )
            ),
            day = VillageDays(
                listOf(
                    DummyDomainModelCreator.createDummyVillageDay().copy(
                        dayChangeDatetime = LocalDateTime.now().minusMinutes(30L)
                    ),
                    DummyDomainModelCreator.createDummyVillageDay()
                )
            )
        )
        val participant = DummyDomainModelCreator.createDummyAliveVillager()
        val chara = DummyDomainModelCreator.createDummyChara().copy(
            id = participant.charaId,
            faceList = listOf(
                CharaFace(CDef.FaceType.通常.code(), CDef.FaceType.通常.alias(), "url")
            )
        )
        val latestDayMesasgeList = (1..20).map {
            DummyDomainModelCreator.createDummyMessage().copy(
                content = DummyDomainModelCreator.createDummyMessageContent().copy(
                    type = MessageType(CDef.MessageType.通常発言)
                )
            )
        }
        val messageContent = MessageContent(
            type = MessageType(CDef.MessageType.通常発言),
            num = null,
            count = null,
            text = "hoge",
            faceCode = CDef.FaceType.通常.code()
        )

        // ## Act ##
        // ## Assert ##
        sayDomainService.assertSay(village, participant, chara, latestDayMesasgeList, messageContent)
    }

    @Test(expected = FirewolfBadRequestException::class)
    fun test_assertSay_発言制限NG_長さ() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中),
            setting = DummyDomainModelCreator.createDummyVillageSettings().copy(
                rules = DummyDomainModelCreator.createDummyVillageRules().copy(
                    messageRestrict = VillageMessageRestricts(
                        true,
                        listOf(
                            VillageMessageRestrict(
                                type = MessageType(CDef.MessageType.通常発言),
                                count = 20,
                                length = 10
                            ),
                            VillageMessageRestrict(
                                type = MessageType(CDef.MessageType.人狼の囁き),
                                count = 40,
                                length = 200
                            )
                        )
                    )
                )
            ),
            day = VillageDays(
                listOf(
                    DummyDomainModelCreator.createDummyVillageDay().copy(
                        dayChangeDatetime = LocalDateTime.now().minusMinutes(30L)
                    ),
                    DummyDomainModelCreator.createDummyVillageDay()
                )
            )
        )
        val participant = DummyDomainModelCreator.createDummyAliveVillager()
        val chara = DummyDomainModelCreator.createDummyChara().copy(
            id = participant.charaId,
            faceList = listOf(
                CharaFace(CDef.FaceType.通常.code(), CDef.FaceType.通常.alias(), "url")
            )
        )
        val latestDayMessageList = (1..19).map {
            DummyDomainModelCreator.createDummyMessage().copy(
                content = DummyDomainModelCreator.createDummyMessageContent().copy(
                    type = MessageType(CDef.MessageType.通常発言)
                )
            )
        }
        val messageContent = MessageContent(
            type = MessageType(CDef.MessageType.通常発言),
            num = null,
            count = null,
            text = "12345678901",
            faceCode = CDef.FaceType.通常.code()
        )

        // ## Act ##
        // ## Assert ##
        sayDomainService.assertSay(village, participant, chara, latestDayMessageList, messageContent)
    }
}