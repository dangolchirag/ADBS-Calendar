package org.infinity.calendar.share.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.snapshotFlow
import org.infinity.calendar.share.calendarModel.CalendarModel
import org.infinity.calendar.share.data.CalendarMonth


@OptIn(ExperimentalFoundationApi::class)
internal suspend fun updateDisplayedMonth(
    pagerState: PagerState,
    onDisplayedMonthChange: (month: CalendarMonth) -> Unit,
    calendarModel: CalendarModel
) {
    snapshotFlow { pagerState.currentPage }.collect { page ->
        onDisplayedMonthChange(
            calendarModel.getMonth(page)
        )
    }
}