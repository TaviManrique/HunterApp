package com.manriquetavi.hunterapp.domain.repository

import androidx.paging.PagingData
import com.manriquetavi.hunterapp.domain.model.Hunter
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHunters(): Flow<PagingData<Hunter>>
    fun searchHunters(query: String): Flow<PagingData<Hunter>>
}