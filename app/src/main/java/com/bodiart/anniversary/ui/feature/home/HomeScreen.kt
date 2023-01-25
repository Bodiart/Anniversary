package com.bodiart.anniversary.ui.feature.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel()
) {
    val state by vm.viewStateFlow.collectAsState()

    LaunchedEffect(key1 = Unit) {
        vm.eventFlow.collect { event ->
            when (event) {
            }
        }
    }

    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.reminderList, { it.id }) { item ->
                ReminderScreenItem(item)
            }
        }
    }
}

@Composable
private fun ReminderScreenItem(item: HomeState.Reminder) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 4.dp,
                color = when (item.anniversaryLevel) { // can be moved into separate func
                    HomeState.AnniversaryLevel.DEFAULT -> Color.Black
                    HomeState.AnniversaryLevel.GOLDEN -> Color.Yellow
                    HomeState.AnniversaryLevel.SILVER -> Color.LightGray
                },
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(text = item.coupleName)
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Wedding:      " + item.weddingDate) // to see anniversary logic clearer
            Text(text = "Anniversary: " + item.anniversaryDate)
        }
    }
}