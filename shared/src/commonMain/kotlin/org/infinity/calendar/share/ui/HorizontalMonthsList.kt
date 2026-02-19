package org.infinity.calendar.share.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.infinity.calendar.share.calendarModel.CalendarModel
import org.infinity.calendar.share.data.CalendarDate
import org.infinity.calendar.share.data.CalendarMonth
import org.infinity.calendar.share.utils.updateDisplayedMonth

@Composable
fun HorizontalMonthsList(
    pagerState: PagerState,
    selectedDate: CalendarDate?,
    onDateSelectionChange: (date: CalendarDate) -> Unit,
    onDisplayedMonthChange: (calendarMonth: CalendarMonth) -> Unit,
    calendarModel: CalendarModel
) {

    HorizontalPager(
        modifier = Modifier.padding(
            start = 4.dp,
            end = 4.dp
        ),
        state = pagerState
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val month = calendarModel.getMonth(it)
            Month(
                month = month,
                onDateSelectionChange = onDateSelectionChange,
                today = calendarModel.today,
                selectedDate = selectedDate
            )
        }
    }

    LaunchedEffect(pagerState) {
        updateDisplayedMonth(
            pagerState = pagerState,
            onDisplayedMonthChange = onDisplayedMonthChange,
            calendarModel = calendarModel
        )
    }
}
