package org.infinity.calendar.share.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.infinity.calendar.share.calendarModel.CalendarModel
import org.infinity.calendar.share.data.CalendarDate
import org.infinity.calendar.share.state.DatePickerState
import org.infinity.calendar.share.state.DatePickerStateImpl
import org.infinity.calendar.share.utils.todayFormattedADDate
import org.infinity.calendar.share.utils.todayFormattedBSDate

@Composable
fun DateContent(
    modifier: Modifier = Modifier,
    state: DatePickerState<CalendarDate>,
    calendarModel: CalendarModel,
    monthPagerState: PagerState,
    onDateSelected: (CalendarDate) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        onDateSelected(calendarModel.today)
    }
    Column(
        modifier = modifier
    ) {
        TodayContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                )
                .clip(
                    shape = MaterialTheme.shapes.small
                ),
            bsDate = calendarModel.todayFormattedBSDate(),
            adDate = calendarModel.todayFormattedADDate(),
            onTodayClick = {
                onDateSelected(calendarModel.today)
                coroutineScope.launch {
                    monthPagerState.scrollToPage(calendarModel.today.page)
                }

            }
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
        )
//        DatePickerContent(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clip(
//                    shape = MaterialTheme.shapes.small
//                ),
//            selectedDate = state.selectedItem,
//            displayedMonth = state.displayedMonth,
//            onDateSelectionChange = {
//                state.select(it)
//                onDateSelected(it)
//            },
//            onDisplayedMonthChange = {
//                state.displayedMonth = it
//            },
//            calendarModel = calendarModel,
//            monthsPagerState = monthPagerState
//        )
    }
}