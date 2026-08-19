package com.sumin.factorialtest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumin.factorialtest.domain.FactorialCalculationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class FactorialViewModel @Inject constructor(
    private val factorialCalculationUseCase: FactorialCalculationUseCase
) : ViewModel() {

    fun calculateFactorial(value: Int) {
        viewModelScope.launch {
            val result = factorialCalculationUseCase(value)
            //TODO
        }
    }
}