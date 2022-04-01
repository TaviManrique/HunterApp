package com.manriquetavi.hunterapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.manriquetavi.hunterapp.data.local.HxHDatabase
import com.manriquetavi.hunterapp.data.paging_source.HunterRemoteMediator
import com.manriquetavi.hunterapp.data.paging_source.SearchHuntersSource
import com.manriquetavi.hunterapp.data.remote.HxHApi
import com.manriquetavi.hunterapp.domain.model.Hunter
import com.manriquetavi.hunterapp.domain.repository.RemoteDataSource
import com.manriquetavi.hunterapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val hxHApi: HxHApi,
    private val hxHDatabase: HxHDatabase
): RemoteDataSource {

    private val hunterDao = hxHDatabase.hunterDao()

    override fun getAllHunters(): Flow<PagingData<Hunter>> {
        val pagingSourceFactory = { hunterDao.getAllHunters() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HunterRemoteMediator(
                hxHApi = hxHApi,
                hxHDatabase = hxHDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHunters(query: String): Flow<PagingData<Hunter>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHuntersSource(
                    hxHApi = hxHApi,
                    query = query
                )
            }
        ).flow
    }

}