package com.ort.firewolf.api.body

import jakarta.validation.constraints.NotNull

data class AdminParticipateBody(
    @field:NotNull(message = "participate_countは必須")
    val participateCount: Int?
) {
    constructor() : this(null) {}
}
