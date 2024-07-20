package com.example.weatherapp.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.ui.compose.WeatherLandingScreen
import com.example.weatherapp.ui.support.Event
import com.example.weatherapp.ui.support.createComposeView
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

class WeatherHostFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        subscribeOnViewModelEvents()
        return createComposeView {
            WeatherLandingScreen(viewModel)
        }
    }

    /**
     * Hides Soft input keyboard
     */
    private fun Fragment.hideSoftKeyboard() {
        try {
            val inputMethodManager = view?.context?.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
        } catch (exception: Exception) {
            // Error in handling hide keyboard due to invalid UI state.
        }
    }

    private fun subscribeOnViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect {
                when (it) {
                    is Event.OnKeyboardClose -> hideSoftKeyboard()
                }
            }
        }
    }

}