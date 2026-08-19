package com.sumin.factorialtest.domain

import javax.inject.Inject

class FactorialCalculationUseCase @Inject constructor(
    private val repository: FactorialRepository
) {
    suspend operator fun invoke(value: Int): String {
        return repository.calculateFactorial(value)
    }
}
