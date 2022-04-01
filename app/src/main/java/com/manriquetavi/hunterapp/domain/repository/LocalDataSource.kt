package com.manriquetavi.hunterapp.domain.repository

import com.manriquetavi.hunterapp.domain.model.Hunter

interface LocalDataSource {
    suspend fun getSelectedHunter(hunterId: Int): Hunter
}