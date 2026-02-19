package org.infinity.calendar.share.state

import org.infinity.calendar.share.data.CalendarDate
import org.infinity.calendar.share.data.CalendarMonth

interface DatePickerState<T>{
    var selection: T?

    var displayedMonth: CalendarMonth

    fun select(date: CalendarDate)

    val selectedDate: CalendarDate?
}