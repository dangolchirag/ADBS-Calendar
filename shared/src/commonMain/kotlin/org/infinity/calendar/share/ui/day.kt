package org.infinity.calendar.share.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.infinity.calendar.share.utils.DaySize

@Composable
fun Day(
    modifier: Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    today: Boolean,
    isSaturday: Boolean = false,
    content: @Composable () -> Unit
) {

    val color = if (today && selected) {
        MaterialTheme.colorScheme.primary
    } else if (today) {
        Color.Transparent
    } else if (isSaturday && selected) {
        MaterialTheme.colorScheme.error
    } else if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Transparent
    }

    val contentColor = if (today && selected) {
        MaterialTheme.colorScheme.onPrimary
    } else if (today) {
        MaterialTheme.colorScheme.primary
    } else if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else if (isSaturday) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.primary
    }

    Surface(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = color,
        contentColor = contentColor,
        border = if (selected || today) {
            BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )
        } else {
            null
        }
    ) {
        Box(
            modifier = Modifier.height(
                height = DaySize
            ).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
