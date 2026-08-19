package com.sumin.factorialtest.di

import android.app.Application
import com.sumin.factorialtest.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        RepositoryModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}