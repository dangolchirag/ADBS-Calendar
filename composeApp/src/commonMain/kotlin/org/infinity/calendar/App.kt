package org.infinity.calendar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import org.infinity.calendar.share.data.CalendarDay
import org.infinity.lib.DateConverter

@Composable
@Preview
fun App() {
    MaterialTheme {
        var date by remember { mutableStateOf("") }
        LaunchedEffect(Unit) {
            val convertedDateBS = DateConverter.adToBs(1990, 6, 22)
            println("Converted Date in bs: $convertedDateBS")
            val convertedDateAD = DateConverter.bsToAd(
                convertedDateBS.year,
                convertedDateBS.month,
                convertedDateBS.day
            )
            println("Converted Date in ad: $convertedDateAD")
            date = convertedDateAD.toString()
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->
            Text(
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
                text = date
            )
        }
    }
}