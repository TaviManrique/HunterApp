package com.manriquetavi.hunterapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.manriquetavi.hunterapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllHunters = useCases.getAllHuntersUseCase()
}