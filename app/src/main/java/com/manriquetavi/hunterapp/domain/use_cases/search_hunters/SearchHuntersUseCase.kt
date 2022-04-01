package com.manriquetavi.hunterapp.domain.use_cases.search_hunters

import androidx.paging.PagingData
import com.manriquetavi.hunterapp.data.repository.Repository
import com.manriquetavi.hunterapp.domain.model.Hunter
import kotlinx.coroutines.flow.Flow

class SearchHuntersUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hunter>>{
        return repository.searchHunters(query = query)
    }
}