package com.bodiart.anniversary.data.usecase

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class GetNextAnniversaryDateUseCaseTest {

    private val useCase = GetNextAnniversaryDateUseCase()

    @Test
    fun `check next anniversary in current year`() {
        val fromDate = LocalDate.of(2000, 5, 10)

        val result = useCase.invoke(
            weddingDate = LocalDate.of(1995, 7, 2),
            fromDate = fromDate
        )

        Assert.assertEquals(LocalDate.of(2000, 7, 2), result)
    }

    @Test
    fun `check next anniversary in next year`() {
        val fromDate = LocalDate.of(2000, 5, 10)

        val result = useCase.invoke(
            weddingDate = LocalDate.of(1995, 4, 2),
            fromDate = fromDate
        )

        Assert.assertEquals(LocalDate.of(2001, 4, 2), result)
    }
}