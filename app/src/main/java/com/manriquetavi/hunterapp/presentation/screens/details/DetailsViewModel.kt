package com.manriquetavi.hunterapp.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.hunterapp.domain.model.Hunter
import com.manriquetavi.hunterapp.domain.use_cases.UseCases
import com.manriquetavi.hunterapp.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedHunter: MutableStateFlow<Hunter?> = MutableStateFlow(null)
    val selectedHunter: StateFlow<Hunter?> = _selectedHunter

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val hunterId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedHunter.value = hunterId?.let { useCases.getSelectedHunterUseCase(hunterId = it) }
            _selectedHunter.value?.name?.let{
                Log.d("Hunter", it)
            }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun generateColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPallet)
        }
    }

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }

}

sealed class UiEvent {
    object GenerateColorPallet: UiEvent()
}