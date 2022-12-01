package com.manriquetavi.hunterapp.data.paging_source

import androidx.paging.*
import androidx.paging.RemoteMediator.*
import androidx.test.core.app.ApplicationProvider
import com.manriquetavi.hunterapp.data.local.HxHDatabase
import com.manriquetavi.hunterapp.data.remote.FakeHxHApi2
import com.manriquetavi.hunterapp.domain.model.Hunter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HunterRemoteMediatorTest {

    private lateinit var hxHApi: FakeHxHApi2
    private lateinit var hxHDatabase: HxHDatabase

    @Before
    fun setup(){
        hxHApi = FakeHxHApi2()
        hxHDatabase = HxHDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useMemory = true
        )
    }

    @After
    fun cleanup() {
        hxHDatabase.clearAllTables()
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runBlocking {
            val remoteMediator = HunterRemoteMediator(
                hxHApi = hxHApi,
                hxHDatabase = hxHDatabase
            )
            val pagingState = PagingState<Int, Hunter>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            Assert.assertTrue(result is MediatorResult.Success)
            Assert.assertFalse((result as MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadSuccessEndOfPaginationTrueWhenNoMoreData() =
        runBlocking {
            hxHApi.clearData()
            val remoteMediator = HunterRemoteMediator(
                hxHApi = hxHApi,
                hxHDatabase = hxHDatabase
            )
            val pagingState = PagingState<Int, Hunter>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            Assert.assertTrue(result is MediatorResult.Success)
            Assert.assertTrue((result as MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runBlocking {
            hxHApi.addException()
            val remoteMediator = HunterRemoteMediator(
                hxHApi = hxHApi,
                hxHDatabase = hxHDatabase
            )
            val pagingState = PagingState<Int, Hunter>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            Assert.assertTrue(result is MediatorResult.Error)
        }
}