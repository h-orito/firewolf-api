package com.ort.firewolf.domain.model.charachip

data class CharaName(
    val name: String,
    val shortName: String
) {
    fun fullName(): String = "[${shortName}] $name"
}