package com.messer_amd.electrician.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Current() {
    var amperageInput by remember { mutableStateOf("") }
    var resistanceInput by remember { mutableStateOf("") }
    var currentResult by remember { mutableStateOf("") }


}