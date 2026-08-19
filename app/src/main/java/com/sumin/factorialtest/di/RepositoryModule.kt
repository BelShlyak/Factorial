package com.sumin.factorialtest.di

import com.sumin.factorialtest.data.FactorialRepositoryImpl
import com.sumin.factorialtest.domain.FactorialRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: FactorialRepositoryImpl): FactorialRepository
}