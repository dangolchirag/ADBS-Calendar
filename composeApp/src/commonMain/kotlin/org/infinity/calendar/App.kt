package org.infinity.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import org.infinity.calendar.share.calendarModel.rememberCalendarModel
import org.infinity.calendar.share.data.CalendarDay
import org.infinity.calendar.share.state.rememberDateRangePickerState
import org.infinity.calendar.share.ui.DateContent
import org.infinity.lib.DateConverter

@Composable
fun App() {
    MaterialTheme {
        var date by remember { mutableStateOf("") }
        LaunchedEffect(Unit) {
            val convertedDateBS = DateConverter.adToBs(1990, 6, 22)
            println("Converted Date in bs: $convertedDateBS")
            val convertedDateAD = DateConverter.bsToAd(
                convertedDateBS.year,
                convertedDateBS.month,
                convertedDateBS.day
            )
            println("Converted Date in ad: $convertedDateAD")
            date = convertedDateAD.toString()
        }
        val calendarModel = rememberCalendarModel()

        val state = rememberDateRangePickerState(
            calendarModel = calendarModel
        )

        val monthPagerState = rememberPagerState(
            initialPage = state.displayedMonth.pageNum,
            initialPageOffsetFraction = 0f,
            pageCount = { calendarModel.getNumberOfMonths() }
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->

            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize()
            ) {
                DateContent(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    state = state,
                    calendarModel = calendarModel,
                    monthPagerState = monthPagerState,
                    onDateSelected = {
                        date = it.toString()
                    }
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = date
                )
            }

        }
    }
}