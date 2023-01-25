package com.bodiart.anniversary.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodiart.anniversary.data.entity.AnniversaryReminder
import com.bodiart.anniversary.data.usecase.CoupleInfoListGetUseCase
import com.bodiart.anniversary.data.usecase.ReminderListCreateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

private const val ANNIVERSARY_YEARS_SILVER = 5
private const val ANNIVERSARY_YEARS_GOLDEN = 10

private const val REMINDER_DAYS_COUNT_UNTIL_ANNIVERSARY = 14L

const val WEDDING_DATE_FORMAT = "yyyy-MM-dd"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coupleInfoListGetUseCase: CoupleInfoListGetUseCase,
    private val reminderListCreateUseCase: ReminderListCreateUseCase
) : ViewModel() {

    private val mutableViewStateFlow = MutableStateFlow(
        HomeState(reminderList = listOf())
    )
    val viewStateFlow = mutableViewStateFlow.asStateFlow()

    private val eventChannel = Channel<HomeEvent>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        setupReminders()
    }

    private fun setupReminders() {
        coupleInfoListGetUseCase.invoke().fold(
            onSuccess = { coupleInfoList ->
                val reminderList = reminderListCreateUseCase.invoke(
                    coupleInfoList,
                    REMINDER_DAYS_COUNT_UNTIL_ANNIVERSARY
                )
                mutableViewStateFlow.update { it.copy(reminderList = mapToScreen(reminderList)) }
            },
            onFailure = {
                // handle error
            }
        )
    }

    // can be separate mapper
    private fun mapToScreen(items: List<AnniversaryReminder>): List<HomeState.Reminder> {
        return items.map {
            HomeState.Reminder(
                id = it.coupleId,
                coupleName = it.coupleId,
                weddingDate = it.weddingDate.format(DateTimeFormatter.ofPattern(WEDDING_DATE_FORMAT)), // can be another format for screen
                anniversaryDate = it.anniversaryDate.format(DateTimeFormatter.ofPattern(WEDDING_DATE_FORMAT)), // can be another format for screen
                anniversaryLevel = when (it.anniversaryDate.year - it.weddingDate.year) {
                    ANNIVERSARY_YEARS_SILVER -> HomeState.AnniversaryLevel.SILVER
                    ANNIVERSARY_YEARS_GOLDEN -> HomeState.AnniversaryLevel.GOLDEN
                    else -> HomeState.AnniversaryLevel.DEFAULT
                }
            )
        }
    }
}