package com.bogdan.core.data.model

data class CreditReportInfo(
    val score: Int,
    val scoreBand: Int,
    val clientRef: String,
    val status: MatchStatus,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val monthsSinceLastDefaulted: Int,
    val hasEverDefaulted: Boolean,
    val percentageCreditUsed: Int,
    val percentageCreditUsedDirectionFlag: Int,
    val changedScore: Int,
    val currentShortTermDebt: Long,
    val currentShortTermNonPromotionalDebt: Long,
    val currentShortTermCreditLimit: Long,
    val currentShortTermCreditUtilisation: Long,
    val changeInShortTermDebt: Long,
    val currentLongTermDebt: Long,
    val currentLongTermNonPromotionalDebt: Long,
    val currentLongTermCreditLimit: Long?,
    val currentLongTermCreditUtilisation: Long?,
    val changeInLongTermDebt: Int,
    val numPositiveScoreFactors: Int,
    val numNegativeScoreFactors: Int,
    val equifaxScoreBand: Int,
    val equifaxScoreBandDescription: String,
    val daysUntilNextReport: Int
)

enum class MatchStatus {
    MATCH,
    NON_MATCH,
}
