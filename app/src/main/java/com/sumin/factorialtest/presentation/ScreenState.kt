package com.sumin.factorialtest.presentation

sealed interface ScreenState {
    data object Initial : ScreenState
    data object Error : ScreenState
    data object Loading : ScreenState
    data class FactorialCalculated(val factorial: String) : ScreenState
}