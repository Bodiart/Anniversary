package com.bodiart.anniversary.data.entity

import java.time.LocalDate

data class AnniversaryReminder(
    val coupleId: String,
    val weddingDate: LocalDate,
    val anniversaryDate: LocalDate // should be string in task requirements, but in real project it's usually some readable type (LocalDate, Date, etc...). You can see string ISO format on screen
)