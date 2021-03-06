package com.ort.firewolf.api.view.charachip

import com.ort.firewolf.domain.model.charachip.Charachips
import com.ort.firewolf.domain.model.charachip.Charas

data class CharachipsView(
    val list: List<CharachipView>
) {
    constructor(
        charachips: Charachips,
        charas: Charas
    ) : this(
        list = charachips.list.map { charachip ->
            CharachipView(
                charachip = charachip,
                charas = charas
            )
        }
    )
}