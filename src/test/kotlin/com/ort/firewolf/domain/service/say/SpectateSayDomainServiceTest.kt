package com.ort.firewolf.domain.service.say

import com.ort.dbflute.allcommon.CDef
import com.ort.firewolf.FirewolfTest
import com.ort.firewolf.domain.model.dead.Dead
import com.ort.firewolf.domain.model.village.VillageStatus
import com.ort.firewolf.domain.model.village.setting.VillageRules
import com.ort.firewolf.domain.service.message.say.SpectateSayDomainService
import com.ort.firewolf.dummy.DummyDomainModelCreator
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SpectateSayDomainServiceTest : FirewolfTest() {

    @Autowired
    lateinit var spectateSayDomainService: SpectateSayDomainService

    // ===================================================================================
    //                                                                                Test
    //                                                                           =========
    @Test
    fun test_isViewable_プロローグ() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.プロローグ)
        )
        val participant = null
        val day = 1

        // ## Act ##
        val isViewable = spectateSayDomainService.isViewable(village, participant, day)

        // ## Assert ##
        assertThat(isViewable).isTrue()
    }

    @Test
    fun test_isViewable_進行中_閲覧できる設定() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中),
            setting = DummyDomainModelCreator.createDummyVillageSettings().copy(
                rules = VillageRules().copy(
                    visibleGraveMessage = true
                )
            )
        )
        val participant = null
        val day = 1

        // ## Act ##
        val isViewable = spectateSayDomainService.isViewable(village, participant, day)

        // ## Assert ##
        assertThat(isViewable).isTrue()
    }

    @Test
    fun test_isViewable_進行中_0日目の発言() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中)
        )
        val participant = null
        val day = 0

        // ## Act ##
        val isViewable = spectateSayDomainService.isViewable(village, participant, day)

        // ## Assert ##
        assertThat(isViewable).isTrue()
    }

    @Test
    fun test_isViewable_進行中_2日目_参加していない() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中)
        )
        val participant = null
        val day = 2

        // ## Act ##
        val isViewable = spectateSayDomainService.isViewable(village, participant, day)

        // ## Assert ##
        assertThat(isViewable).isFalse()
    }

    @Test
    fun test_isViewable_進行中_2日目_見学() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中)
        )
        val participant = DummyDomainModelCreator.createDummyVillageParticipant().copy(
            isSpectator = true
        )
        val day = 2

        // ## Act ##
        val isViewable = spectateSayDomainService.isViewable(village, participant, day)

        // ## Assert ##
        assertThat(isViewable).isTrue()
    }

    @Test
    fun test_isViewable_進行中_2日目_見学でない_生存() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中)
        )
        val participant = DummyDomainModelCreator.createDummyAliveVillager()
        val day = 2

        // ## Act ##
        val isViewable = spectateSayDomainService.isViewable(village, participant, day)

        // ## Assert ##
        assertThat(isViewable).isFalse()
    }

    @Test
    fun test_isViewable_進行中_2日目_見学でない_突然死() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中)
        )
        val participant = DummyDomainModelCreator.createDummyDeadVillager().copy(
            dead = Dead(CDef.DeadReason.突然, DummyDomainModelCreator.createDummyVillageDay())
        )
        val day = 2

        // ## Act ##
        val isViewable = spectateSayDomainService.isViewable(village, participant, day)

        // ## Assert ##
        assertThat(isViewable).isFalse()
    }

    @Test
    fun test_isViewable_進行中_2日目_見学でない_処刑() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.進行中)
        )
        val participant = DummyDomainModelCreator.createDummyDeadVillager().copy(
            dead = Dead(CDef.DeadReason.処刑, DummyDomainModelCreator.createDummyVillageDay())
        )
        val day = 2

        // ## Act ##
        val isViewable = spectateSayDomainService.isViewable(village, participant, day)

        // ## Assert ##
        assertThat(isViewable).isTrue()
    }
}