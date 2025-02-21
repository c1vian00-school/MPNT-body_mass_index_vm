package com.example.bodymassindexvm

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import android.icu.text.DecimalFormat

class BmiViewModel : ViewModel() {
    private val _heightInput = mutableStateOf("")
    val heightInput: State<String> = _heightInput

    private val _weightInput = mutableStateOf("")
    val weightInput: State<String> = _weightInput

    private val _bmiResult = mutableStateOf("0.0")
    val bmiResult: State<String> = _bmiResult

    // Update functions
    fun setHeight(input: String) {
        _heightInput.value = input.replace(',', '.')
        calculateBmi()
    }

    fun setWeight(input: String) {
        _weightInput.value = input
        calculateBmi()
    }

    private fun calculateBmi() {
        val height = _heightInput.value.toFloatOrNull() ?: 0.0f
        val weight = _weightInput.value.toIntOrNull() ?: 0
        val formatter = DecimalFormat("0.00")

        _bmiResult.value = if (weight > 0 && height > 0) {
            formatter.format(weight / (height * height))
        } else {
            "0.0"
        }
    }
}
