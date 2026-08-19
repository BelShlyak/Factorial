package com.sumin.factorialtest.data

import com.sumin.factorialtest.domain.FactorialRepository
import javax.inject.Inject

class FactorialRepositoryImpl @Inject constructor(

) : FactorialRepository {
    override suspend fun calculateFactorial(value: Int): String {
        return "calculation of the factorial"
    }
}