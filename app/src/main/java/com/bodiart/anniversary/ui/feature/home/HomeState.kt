package com.bodiart.anniversary.ui.feature.home

data class HomeState(
    val reminderList: List<Reminder>
) {

    data class Reminder(
        val id: String,
        val coupleName: String,
        val weddingDate: String,
        val anniversaryDate: String,
        val anniversaryLevel: AnniversaryLevel
    )

    enum class AnniversaryLevel {
        GOLDEN,
        SILVER,
        DEFAULT
    }
}