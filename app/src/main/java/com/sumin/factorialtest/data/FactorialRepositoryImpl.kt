package com.sumin.factorialtest.data

import android.app.Application
import com.sumin.factorialtest.R
import com.sumin.factorialtest.domain.FactorialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import java.math.BigInteger
import javax.inject.Inject

class FactorialRepositoryImpl @Inject constructor(
    private val application: Application
) : FactorialRepository {
    override suspend fun calculateFactorial(value: Int): String = withContext(Dispatchers.Default) {
        require(value >= 0) {
            application.getString(R.string.factorial_is_not_defined_for_negative_numbers)
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