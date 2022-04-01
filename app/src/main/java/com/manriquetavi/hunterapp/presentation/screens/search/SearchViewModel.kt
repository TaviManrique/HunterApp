package com.manriquetavi.hunterapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.manriquetavi.hunterapp.domain.model.Hunter
import com.manriquetavi.hunterapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedHunters = MutableStateFlow<PagingData<Hunter>>(PagingData.empty())
    val searchedHunters = _searchedHunters

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHunters(query: String) {
        viewModelScope.launch {
            useCases.searchHuntersUseCase(query = query).cachedIn(viewModelScope).collect {
                _searchedHunters.value = it
            }
        }
    }
}