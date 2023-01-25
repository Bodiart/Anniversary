package com.bodiart.anniversary.data.usecase

import com.bodiart.anniversary.data.entity.CoupleInfo
import com.bodiart.anniversary.ui.feature.home.WEDDING_DATE_FORMAT
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CoupleInfoListGetUseCase @Inject constructor() {

    fun invoke() = kotlin.runCatching {
        mock()
    }

    private fun mock(): List<CoupleInfo> = (0 until 100).map { mockItem(it) }

    private fun mockItem(index: Int) = CoupleInfo(
        id = index.toString(),
        weddingDateIso = mockWeddingDayIso(index)
    )

    private fun mockWeddingDayIso(index: Int): String {
        val now = LocalDate.of(2010, 1, 1)

        val formatter = DateTimeFormatter.ofPattern(WEDDING_DATE_FORMAT)
        return now.plusMonths(index.toLong())
            .plusDays(index.toLong()).format(formatter)
    }
}