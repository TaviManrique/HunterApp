package com.manriquetavi.hunterapp.domain.use_cases.get_all_hunters

import androidx.paging.PagingData
import com.manriquetavi.hunterapp.data.repository.Repository
import com.manriquetavi.hunterapp.domain.model.Hunter
import kotlinx.coroutines.flow.Flow

class GetAllHuntersUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hunter>> {
        return repository.getAllHunters()
    }
}