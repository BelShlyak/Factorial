package com.sumin.factorialtest

import android.app.Application
import com.sumin.factorialtest.di.DaggerApplicationComponent

class FactorialApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(
                this
            )
    }
}