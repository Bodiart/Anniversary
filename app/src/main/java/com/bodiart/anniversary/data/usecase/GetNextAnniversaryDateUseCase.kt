package com.bodiart.anniversary.data.usecase

import java.time.LocalDate
import javax.inject.Inject

class GetNextAnniversaryDateUseCase @Inject constructor() {

    fun invoke(weddingDate: LocalDate, fromDate: LocalDate = LocalDate.now()): LocalDate {
        var anniversaryDate =
            LocalDate.of(fromDate.year, weddingDate.month, weddingDate.dayOfMonth)

        // next anniversary cant be before 'fromDate'
        if (anniversaryDate.isBefore(fromDate)) {
            anniversaryDate =
                LocalDate.of(fromDate.year + 1, weddingDate.month, weddingDate.dayOfMonth)
        }
        return anniversaryDate
    }
}