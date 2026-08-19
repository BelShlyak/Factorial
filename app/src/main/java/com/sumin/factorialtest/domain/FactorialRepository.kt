package com.sumin.factorialtest.domain

interface FactorialRepository {
    suspend fun calculateFactorial(value: Int): String
}