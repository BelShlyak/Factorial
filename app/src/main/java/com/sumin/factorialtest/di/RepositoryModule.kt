package com.sumin.factorialtest.di

import com.sumin.factorialtest.data.FactorialRepositoryImpl
import com.sumin.factorialtest.domain.FactorialRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRepository(impl: FactorialRepositoryImpl): FactorialRepository
}
