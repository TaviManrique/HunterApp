package com.manriquetavi.hunterapp.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.manriquetavi.hunterapp.data.local.HxHDatabase
import com.manriquetavi.hunterapp.data.remote.HxHApi
import com.manriquetavi.hunterapp.domain.model.Hunter
import com.manriquetavi.hunterapp.domain.model.HunterRemoteKeys
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ExperimentalPagingApi
class HunterRemoteMediator(
    private val hxHApi: HxHApi,
    private val hxHDatabase: HxHDatabase
): RemoteMediator<Int, Hunter>() {

    private val hunterDao = hxHDatabase.hunterDao()
    private val hunterRemoteKeysDao = hxHDatabase.hunterRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = hunterRemoteKeysDao.getRemoteKeys(hunterId = 1)?.lastUpdated ?: 0L
        val cacheTimeOut = 5 //minutes
        
        Log.d("RemotedMediator", "Current Time: ${parseMillis(currentTime)}")
        Log.d("RemotedMediator", "Last Updated Time: ${parseMillis(lastUpdated)}")
        val diffInMinutes = (currentTime - lastUpdated)/60000
        return if(diffInMinutes.toInt() <= cacheTimeOut) {
            Log.d("RemotedMediator", "UP TO DATE!")
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            Log.d("RemotedMediator", "REFRESH")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hunter>): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosesToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPages = remoteKeys?.prevPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPages
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nexPage = remoteKeys?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nexPage
                }
            }
            val response = hxHApi.getAllHunters(page = page)
            if (response.hunters.isNotEmpty()) {
                hxHDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        hunterDao.deleteAllHunters()
                        hunterRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val lastUpdated = response.lastUpdated
                    val keys = response.hunters.map { hunter ->
                        HunterRemoteKeys(
                            id = hunter.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = lastUpdated
                        )
                    }
                    hunterRemoteKeysDao.addAllRemoteKeys(hunterRemoteKeys = keys)
                    hunterDao.addHunters(hunters = response.hunters)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosesToCurrentPosition(
        state: PagingState<Int, Hunter>
    ): HunterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                hunterRemoteKeysDao.getRemoteKeys(hunterId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Hunter>
    ): HunterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { hunter ->
            hunterRemoteKeysDao.getRemoteKeys(hunterId = hunter.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Hunter>
    ): HunterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { hunter ->
            hunterRemoteKeysDao.getRemoteKeys(hunterId = hunter.id)

        }
    }

    private fun parseMillis(millis: Long): String {
        val date = Date(millis)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
        return format.format(date)
    }
}