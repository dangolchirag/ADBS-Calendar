package org.infinity.calendar.share.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.infinity.calendar.share.utils.RecommendedSizeForAccessibility

@Composable
fun WeekDays() {

    val weekdayNames = arrayListOf(
        "Sun",
        "Mon",
        "Tue",
        "Wed",
        "Thu",
        "Fri",
        "Sat"
    )
    Row(
        modifier = Modifier
            .defaultMinSize(
                minHeight = RecommendedSizeForAccessibility
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        weekdayNames.forEachIndexed { index, name ->
            Box(
                modifier = Modifier
                    .size(
                        width = RecommendedSizeForAccessibility,
                        height = RecommendedSizeForAccessibility
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                    modifier = Modifier,
                    color = if (index == 6) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
