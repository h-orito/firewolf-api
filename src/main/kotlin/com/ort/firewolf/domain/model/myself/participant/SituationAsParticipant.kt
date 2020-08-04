package com.ort.firewolf.domain.model.myself.participant

data class SituationAsParticipant(
    val participate: VillageParticipateSituation,
    val skillRequest: VillageSkillRequestSituation,
    val commit: VillageCommitSituation,
    val say: VillageSaySituation,
    val ability: VillageAbilitySituations,
    val vote: VillageVoteSituation,
    val creator: VillageCreatorSituation
)