package com.bogdan.core.data.model

data class UserInfo(
    val accountIDVStatus: PassStatus?,
    val creditReportInfo: CreditReportInfo?,
    val dashboardStatus: PassStatus?,
    val personaType: PersonaType?,
    val coachingSummary: CoachingSummary?,
    val augmentedCreditScore: String?
)

