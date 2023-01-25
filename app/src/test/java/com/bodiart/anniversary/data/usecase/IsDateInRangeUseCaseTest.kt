package com.bodiart.anniversary.data.usecase

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class IsDateInRangeUseCaseTest {

    private val useCase = IsDateInRangeUseCase()

    @Test
    fun `check date is in range`() {
        val rangeStart = LocalDate.of(2000, 1, 1)
        val rangeEnd = LocalDate.of(2000, 2, 10)

        Assert.assertEquals(true, useCase.invoke(LocalDate.of(2000, 1, 1), rangeStart, rangeEnd))
        Assert.assertEquals(true, useCase.invoke(LocalDate.of(2000, 1, 10), rangeStart, rangeEnd))
        Assert.assertEquals(true, useCase.invoke(LocalDate.of(2000, 2, 5), rangeStart, rangeEnd))
        Assert.assertEquals(false, useCase.invoke(LocalDate.of(2000, 2, 15), rangeStart, rangeEnd))
        Assert.assertEquals(false, useCase.invoke(LocalDate.of(2000, 2, 25), rangeStart, rangeEnd))
        Assert.assertEquals(false, useCase.invoke(LocalDate.of(2000, 3, 8), rangeStart, rangeEnd))
    }
}