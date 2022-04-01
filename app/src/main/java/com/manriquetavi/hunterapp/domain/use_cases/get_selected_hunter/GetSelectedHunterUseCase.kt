package com.manriquetavi.hunterapp.domain.use_cases.get_selected_hunter

import com.manriquetavi.hunterapp.data.repository.Repository
import com.manriquetavi.hunterapp.domain.model.Hunter

class GetSelectedHunterUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(hunterId: Int): Hunter {
        return repository.getSelectedHunter(hunterId = hunterId)
    }
}