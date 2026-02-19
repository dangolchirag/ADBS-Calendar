package org.infinity.calendar.share.ui

import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.infinity.calendar.share.calendarModel.CalendarModel
import org.infinity.calendar.share.data.CalendarDate
import org.infinity.calendar.share.data.CalendarMonth
import org.infinity.calendar.share.utils.DatePickerHorizontalPadding
import org.infinity.calendar.share.utils.MaxCalendarRows
import org.infinity.calendar.share.utils.RecommendedSizeForAccessibility
import org.infinity.calendar.share.utils.noRippleClickable

@Composable
fun DatePickerContent(
    modifier: Modifier,
    monthsPagerState: PagerState,
    selectedDate: CalendarDate?,
    displayedMonth: CalendarMonth,
    onDateSelectionChange: (calendarDate: CalendarDate) -> Unit,
    onDisplayedMonthChange: (calendarMonth: CalendarMonth) -> Unit,
    calendarModel: CalendarModel
) {


    val coroutineScope = rememberCoroutineScope()
    var yearPickerExpand by rememberSaveable { mutableStateOf(false) }
    var monthPickerExpand by rememberSaveable { mutableStateOf(false) }

    val yearRange = remember {
        calendarModel.getYearRange().map { it.toString() }
    }
    val monthRange = arrayListOf(
        "Baisakh",
        "Jestha",
        "Ashadh",
        "Shrawan",
        "Bhadra",
        "Ashwin",
        "Kartik",
        "Mangsir",
        "Poush",
        "Magh",
        "Falgun",
        "Chaitra"
    )


    Column(
        modifier = modifier
    ) {
        MonthNavigation(
            modifier = Modifier.fillMaxWidth(),
            displayMonth = displayedMonth,
            nextAvailable = monthsPagerState.canScrollForward,
            previousAvailable = monthsPagerState.canScrollBackward,
            monthPickerExpand = monthPickerExpand,
            yearPickerExpand = yearPickerExpand,
            onPreviousClick = {
                coroutineScope.launch {
                    try {
                        monthsPagerState.animateScrollToPage(
                            page = monthsPagerState.currentPage - 1
                        )
                    } catch (_: IllegalArgumentException) {
                        // Ignore. This may happen if the user clicked the "next" arrow fast while
                        // the list was still animating to the next item.
                    }
                }
            },
            onNextClick = {
                coroutineScope.launch {
                    try {
                        monthsPagerState.animateScrollToPage(
                            page = monthsPagerState.currentPage + 1
                        )
                    } catch (_: IllegalArgumentException) {
                        // Ignore. This may happen if the user clicked the "next" arrow fast while
                        // the list was still animating to the next item.
                    }
                }
            },
            onYearClick = {
                monthPickerExpand = false
                yearPickerExpand = !yearPickerExpand
            },
            onMonthClick = {
                yearPickerExpand = false
                monthPickerExpand = !monthPickerExpand
            }
        )
        Box {
            Column {
                WeekDays()
                HorizontalMonthsList(
                    pagerState = monthsPagerState,
                    selectedDate = selectedDate,
                    onDateSelectionChange = onDateSelectionChange,
                    onDisplayedMonthChange = onDisplayedMonthChange,
                    calendarModel = calendarModel
                )
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = yearPickerExpand || monthPickerExpand,
                modifier = Modifier.clipToBounds(),
                enter = expandVertically() + fadeIn(initialAlpha = 0.6f),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .requiredHeight(
                                height = RecommendedSizeForAccessibility * (MaxCalendarRows + 1) -
                                        DividerDefaults.Thickness - 24.dp
                            )
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = DatePickerHorizontalPadding),
                        columns = GridCells.Fixed(
                            count = 3
                        )
                    ) {
                        if (yearPickerExpand) {
                            items(items = yearRange, key = { it }) { year ->
                                ExpandItem(
                                    modifier = Modifier.noRippleClickable {
                                        coroutineScope.launch {
                                            monthsPagerState.animateScrollToPage(
                                                page = calendarModel.getPage(
                                                    year.toInt(), displayedMonth.month
                                                )
                                            )
                                            delay(200)
                                            yearPickerExpand = !yearPickerExpand
                                        }
                                    },
                                    selected = year == displayedMonth.year.toString(),
                                    text = year
                                )
                            }
                        } else {
                            items(items = monthRange, key = { it }) { month ->
                                ExpandItem(
                                    modifier = Modifier.noRippleClickable {
                                        coroutineScope.launch {
                                            monthsPagerState.animateScrollToPage(
                                                page = calendarModel.getPage(
                                                    displayedMonth.year,
                                                    monthRange.indexOf(month) + 1
                                                )
                                            )
                                            delay(200)
                                            monthPickerExpand = !monthPickerExpand
                                        }
                                    },
                                    selected = monthRange.indexOf(month) + 1 == displayedMonth.month,
                                    text = month
                                )
                            }
                        }
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}
