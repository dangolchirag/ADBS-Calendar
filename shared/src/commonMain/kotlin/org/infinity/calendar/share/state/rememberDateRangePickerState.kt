package org.infinity.calendar.share.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.infinity.calendar.share.calendarModel.CalendarModel
import org.infinity.calendar.share.data.CalendarMonth

@Composable
fun rememberDateRangePickerState(
    calendarModel: CalendarModel,
    initialDisplayMonth: CalendarMonth? = null,
): DatePickerStateImpl {

    return remember {
        DatePickerStateImpl(
            initialDisplayMonth = initialDisplayMonth,
            calendarModel = calendarModel
        )
    }

}