package com.sumin.factorialtest.di

import androidx.lifecycle.ViewModel
import com.sumin.factorialtest.presentation.FactorialViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(FactorialViewModel::class)
    @Binds
    fun bindViewModel(impl: FactorialViewModel): ViewModel
}