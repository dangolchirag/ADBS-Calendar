package org.infinity.calendar.share.calendarModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberCalendarModel(): CalendarModel {
    return remember { BikramSambatCalendarModel() }
}