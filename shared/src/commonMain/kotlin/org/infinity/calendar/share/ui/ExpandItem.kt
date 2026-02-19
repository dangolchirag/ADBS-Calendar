package org.infinity.calendar.share.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpandItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    text: String
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier.background(
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.extraLarge
            ).padding(
                vertical = 16.dp,
                horizontal = 24.dp
            ).wrapContentSize(),
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center
        )
    }

}
