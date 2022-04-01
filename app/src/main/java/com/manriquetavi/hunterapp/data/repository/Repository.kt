package com.manriquetavi.hunterapp.data.repository

import androidx.paging.PagingData
import com.manriquetavi.hunterapp.domain.model.Hunter
import com.manriquetavi.hunterapp.domain.repository.DataStoreOperations
import com.manriquetavi.hunterapp.domain.repository.LocalDataSource
import com.manriquetavi.hunterapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllHunters(): Flow<PagingData<Hunter>> {
        return remote.getAllHunters()
    }

    fun searchHunters(query: String): Flow<PagingData<Hunter>> {
        return remote.searchHunters(query = query)
    }

    suspend fun getSelectedHunter(hunterId: Int): Hunter {
        return local.getSelectedHunter(hunterId = hunterId)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}