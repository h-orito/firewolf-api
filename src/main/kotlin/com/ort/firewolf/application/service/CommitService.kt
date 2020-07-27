package com.ort.firewolf.application.service

import com.ort.firewolf.domain.model.commit.Commit
import com.ort.firewolf.domain.model.commit.Commits
import com.ort.firewolf.domain.model.village.Village
import com.ort.firewolf.domain.model.village.participant.VillageParticipant
import com.ort.firewolf.infrastructure.datasource.commit.CommitDataSource
import org.springframework.stereotype.Service

@Service
class CommitService(
    val commitDataSource: CommitDataSource
) {
    fun findCommits(villageId: Int): Commits = commitDataSource.findCommits(villageId)

    fun findCommit(village: Village, participant: VillageParticipant?): Commit? {
        participant ?: return null
        return commitDataSource.findCommit(village, participant)
    }

    /**
     * コミット/取り消し
     * @param commit commit
     */
    fun updateCommit(commit: Commit) = commitDataSource.updateCommit(commit)
}