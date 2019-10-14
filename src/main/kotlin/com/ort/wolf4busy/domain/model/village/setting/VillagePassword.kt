package com.ort.wolf4busy.domain.model.village.setting

data class VillagePassword(
    val joinPasswordRequired: Boolean,
    val joinPassword: String?
) {
    companion object {
        operator fun invoke (
            joinPassword: String?
        ) :VillagePassword{
            return VillagePassword(
                joinPasswordRequired = !joinPassword.isNullOrEmpty(),
                joinPassword = joinPassword
            )
        }
    }
}