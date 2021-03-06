package com.ort.firewolf.domain.model.daychange

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ort.dbflute.allcommon.CDef
import com.ort.firewolf.FirewolfTest
import com.ort.firewolf.domain.model.message.Messages
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.VillageStatus
import com.ort.firewolf.domain.model.village.ability.VillageAbilities
import com.ort.firewolf.domain.model.village.vote.VillageVotes
import com.ort.firewolf.dummy.DummyDomainModelCreator
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class DayChangeTest : FirewolfTest() {

    @Test
    fun test_setIsChange_最初から変更あり() {
        // ## Arrange ##
        val village = DummyDomainModelCreator.createDummyVillage().copy(
            status = VillageStatus(CDef.VillageStatus.終了)
        )
        val dayChange = DayChange(
            village = village,
            votes = DummyDomainModelCreator.createDummyVillageVotes(),
            abilities = DummyDomainModelCreator.createDummyVillageAbilities(),
            players = DummyDomainModelCreator.createDummyPlayers()
        ).copy(isChange = true)

        // ## Act ##
        val afterDayChange = dayChange.setIsChange(dayChange)

        // ## Assert ##
        assertThat(afterDayChange.isChange).`as`("最初から変更ありなら変更あり").isTrue()
    }

    @Test
    fun test_setIsChange_変更あり() {
        // ## Arrange ##
        val village = mock<Village> { on { existsDifference(any()) } doReturn false }
        val votes = mock<VillageVotes> { on { existsDifference(any()) } doReturn false }
        val abilities = mock<VillageAbilities> { on { existsDifference(any()) } doReturn false }
        val dayChange = DayChange(
            village = village,
            votes = votes,
            abilities = abilities,
            players = DummyDomainModelCreator.createDummyPlayers()
        ).copy(
            messages = mock<Messages> { on { existsDifference(any()) } doReturn true }
        )

        // ## Act ##
        val afterDayChange = dayChange.setIsChange(dayChange)

        // ## Assert ##
        assertThat(afterDayChange.isChange).isTrue()
    }
}