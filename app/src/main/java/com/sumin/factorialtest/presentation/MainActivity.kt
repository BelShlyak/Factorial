package com.sumin.factorialtest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sumin.factorialtest.FactorialApp
import com.sumin.factorialtest.databinding.ActivityMainBinding
import com.sumin.factorialtest.di.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    private val viewModel: FactorialViewModel by lazy {
        ViewModelProvider(this, viewmodelFactory)[FactorialViewModel::class]
    }

    private val component by lazy {
        (application as FactorialApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}