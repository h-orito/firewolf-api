package com.ort.firewolf.api.form

data class VillageListForm(
    val village_status: List<String>?,
    val is_auto_generate: Boolean?
) {
    constructor() : this(null, null)
}
