package me.buddhabhu.panpage.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PanViewModel: ViewModel() {

    val isPanValid = mutableStateOf(true)
    val isBirthDateValid = mutableStateOf(true)
}