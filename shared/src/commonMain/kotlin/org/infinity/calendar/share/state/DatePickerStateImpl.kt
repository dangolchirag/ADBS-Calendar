package org.infinity.calendar.share.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.infinity.calendar.share.calendarModel.CalendarModel
import org.infinity.calendar.share.data.CalendarDate
import org.infinity.calendar.share.data.CalendarMonth

@Stable
class DatePickerStateImpl(
    initialDisplayMonth: CalendarMonth?,
    calendarModel: CalendarModel
) : DatePickerState<CalendarDate> {
    var selectedItem: CalendarDate? by mutableStateOf(calendarModel.today)
    override val selectedDate: CalendarDate? = null
    override var selection: CalendarDate?
        get() {
            return selectedItem
        }
        set(value) {
            selectedItem = value
        }
    override var displayedMonth: CalendarMonth by mutableStateOf(
        initialDisplayMonth ?: calendarModel.getMonth(
            calendarModel.today
        )
    )

    override fun select(date: CalendarDate) {
        selectedItem = date
    }

}
