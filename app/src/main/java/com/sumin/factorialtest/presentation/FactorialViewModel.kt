package com.sumin.factorialtest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumin.factorialtest.domain.FactorialCalculationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactorialViewModel @Inject constructor(
    private val factorialCalculationUseCase: FactorialCalculationUseCase
) : ViewModel() {

    private val requestFlow = MutableSharedFlow<Int>()

    private val _state = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val state = _state.asStateFlow()

    fun calculateFactorial(value: Int) {
        viewModelScope.launch {
            requestFlow.emit(value)
        }
    }

    init {
        requestFlow
            .onEach {
                _state.emit(ScreenState.Loading)
            }
            .map {
                try {
                    val result = factorialCalculationUseCase(it)
                    ScreenState.FactorialCalculated(result)
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    ScreenState.Error
                }
            }
            .onEach { newState ->
                _state.emit(newState)
            }.launchIn(viewModelScope)
    }
}
