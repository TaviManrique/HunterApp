package com.manriquetavi.hunterapp.data.repository

import com.manriquetavi.hunterapp.data.local.HxHDatabase
import com.manriquetavi.hunterapp.domain.model.Hunter
import com.manriquetavi.hunterapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(hxHDatabase: HxHDatabase): LocalDataSource {

    private val hunterDao = hxHDatabase.hunterDao()

    override suspend fun getSelectedHunter(hunterId: Int): Hunter {
        return hunterDao.getSelectedHunter(hunterId = hunterId)
    }

}