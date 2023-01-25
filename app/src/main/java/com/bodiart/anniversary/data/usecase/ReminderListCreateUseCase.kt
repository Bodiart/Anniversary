package com.bodiart.anniversary.data.usecase

import com.bodiart.anniversary.data.entity.AnniversaryReminder
import com.bodiart.anniversary.data.entity.CoupleInfo
import com.bodiart.anniversary.ui.feature.home.WEDDING_DATE_FORMAT
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ReminderListCreateUseCase @Inject constructor(
    private val getNextAnniversaryDateUseCase: GetNextAnniversaryDateUseCase,
    private val isDateInRangeUseCase: IsDateInRangeUseCase
) {

    fun invoke(infoList: List<CoupleInfo>, daysUntilAnniversary: Long): List<AnniversaryReminder> {
        val reminderRangeStart = LocalDate.now()
        val reminderRangeEnd = reminderRangeStart.plusDays(daysUntilAnniversary)

        return infoList.mapNotNull { coupleInfo ->
            val weddingDate = coupleInfo.weddingDateIso?.toLocalDate() ?: return@mapNotNull null

            val anniversaryDate = getNextAnniversaryDateUseCase.invoke(weddingDate)
            val isInReminderRange = isDateInRangeUseCase.invoke(
                anniversaryDate,
                reminderRangeStart,
                reminderRangeEnd
            )

            if (isInReminderRange) {
                AnniversaryReminder(
                    coupleId = coupleInfo.id,
                    weddingDate = weddingDate,
                    anniversaryDate = anniversaryDate
                )
            } else {
                null
            }
        }
    }

    private fun String.toLocalDate(): LocalDate? = try {
        LocalDate.parse(this, DateTimeFormatter.ofPattern(WEDDING_DATE_FORMAT))
    } catch (e: Exception) {
        null
    }
}