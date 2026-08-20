package com.sumin.factorialtest.data

import com.sumin.factorialtest.domain.FactorialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import java.math.BigInteger
import javax.inject.Inject

class FactorialRepositoryImpl @Inject constructor(
) : FactorialRepository {
    override suspend fun calculateFactorial(value: Int): String = withContext(Dispatchers.Default) {
        require(value >= 0) {
            "Factorial is not defined for negative values"
        }
        var result = BigInteger.ONE
        for (factor in 2..value) {
            ensureActive()
            result = result.multiply(
                BigInteger.valueOf(factor.toLong())
            )
        }
        result.toString()
    }
}