package org.infinity.calendar.share.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DayItem(
    isBS: Boolean,
    dateInAD: String,
    dateInBS: String,
    isHoliday: Boolean,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = dateInBS,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )

//        Text(
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(horizontal = MaterialTheme.dimens.small1)
//                .padding(bottom = MaterialTheme.dimens.small1 / 2),
//            text = dateInAD,
//            textAlign = TextAlign.Right,
//            style = MaterialTheme.typography.bodySmall.copy(
//                color = if (isHoliday && isSelected) MaterialTheme.colorScheme.onPrimary else if (isHoliday) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondaryTextColor,
//                fontSize = 10.sp
//            )
//        )
    }
}