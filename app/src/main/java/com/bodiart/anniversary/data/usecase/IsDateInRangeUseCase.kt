package com.bodiart.anniversary.data.usecase

import java.time.LocalDate
import javax.inject.Inject

class IsDateInRangeUseCase @Inject constructor(){

    fun invoke(date: LocalDate, rangeStart: LocalDate, rangeEnd: LocalDate): Boolean {
        return (date.isAfter(rangeStart) || date.isEqual(rangeStart))
                && (date.isBefore(rangeEnd) || date.isEqual(rangeEnd))
    }
}