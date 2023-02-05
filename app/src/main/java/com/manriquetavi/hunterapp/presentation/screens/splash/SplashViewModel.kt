package com.manriquetavi.hunterapp.presentation.screens.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.hunterapp.domain.use_cases.UseCases
import com.manriquetavi.hunterapp.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Screen.Welcome.route)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.readOnBoardingUseCase().collect { completed ->
                startDestination = if (completed) Screen.Home.route else Screen.Welcome.route
            }
            isLoading = false
        }
    }
}