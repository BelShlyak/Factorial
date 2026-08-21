package com.sumin.factorialtest.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sumin.factorialtest.FactorialApp
import com.sumin.factorialtest.R
import com.sumin.factorialtest.databinding.ActivityMainBinding
import com.sumin.factorialtest.di.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FactorialViewModel by viewModels { viewModelFactory }

    private val component by lazy {
        (application as FactorialApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeState()
        binding.buttonCalculate.setOnClickListener {
            val input = binding.editTextNumber.text.toString().trim()
            if (isInputEmpty(input)) {
                return@setOnClickListener
            }
            val value = validateValue(input.toIntOrNull()) ?: return@setOnClickListener
            viewModel.calculateFactorial(value)
        }
    }

    private fun isInputEmpty(input: String): Boolean {
        if (input.isEmpty()) {
            Toast.makeText(
                this,
                getString(R.string.enter_the_number),
                Toast.LENGTH_SHORT
            ).show()
            return true
        }
        return false
    }

    private fun validateValue(value: Int?): Int? {
        if (value == null) {
            Toast.makeText(
                this,
                getString(R.string.incorrect_or_too_large_number_has_been_entered),
                Toast.LENGTH_SHORT
            ).show()
        }
        return value
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    renderState(it)
                }
            }
        }
    }

    private fun renderState(screenState: ScreenState) {
        when (screenState) {
            ScreenState.Error -> {
                with(binding) {
                    progressBarLoading.isVisible = false
                    buttonCalculate.isEnabled = true
                    editTextNumber.isEnabled = true
                }
                Toast.makeText(
                    this,
                    getString(R.string.factorial_calculation_error),
                    Toast.LENGTH_SHORT
                ).show()
            }

            is ScreenState.FactorialCalculated -> {
                with(binding) {
                    progressBarLoading.isVisible = false
                    buttonCalculate.isEnabled = true
                    editTextNumber.isEnabled = true
                    textViewFactorial.text = screenState.factorial
                }
            }

            ScreenState.Initial -> {
                with(binding) {
                    progressBarLoading.isVisible = false
                    buttonCalculate.isEnabled = true
                    editTextNumber.isEnabled = true
                    textViewFactorial.text = ""
                }
            }

            ScreenState.Loading -> {
                with(binding) {
                    progressBarLoading.isVisible = true
                    buttonCalculate.isEnabled = false
                    editTextNumber.isEnabled = false
                }
            }
        }
    }
}
