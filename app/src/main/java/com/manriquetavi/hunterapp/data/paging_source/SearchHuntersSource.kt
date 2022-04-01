package com.manriquetavi.hunterapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.manriquetavi.hunterapp.data.remote.HxHApi
import com.manriquetavi.hunterapp.domain.model.Hunter
import javax.inject.Inject

class SearchHuntersSource(
    private val hxHApi: HxHApi,
    private val query: String
): PagingSource<Int, Hunter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hunter> {
        return try {
            val apiResponse = hxHApi.searchHunters(name = query)
            val hunters = apiResponse.hunters
            if (hunters.isNotEmpty()) {
                LoadResult.Page(
                    data = hunters,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hunter>): Int? {
        return state.anchorPosition
    }
}