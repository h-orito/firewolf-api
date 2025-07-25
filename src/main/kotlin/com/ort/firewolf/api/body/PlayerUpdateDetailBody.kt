package com.ort.firewolf.api.body

import jakarta.validation.constraints.NotNull

data class PlayerUpdateDetailBody(
    @field:NotNull(message = "nicknameは必須")
    val nickname: String,
    val otherSiteName: String?,
    val introduction: String?
) {
    constructor() : this("", null, null)
}
