package com.ahanafrifat.paybackcodingchallenge.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahanafrifat.paybackcodingchallenge.data.remote.PaybackApi
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.utils.log
import javax.inject.Inject

class SearchHitsSource @Inject constructor(
    private val api: PaybackApi,
    private val query: String?
) : PagingSource<Int, Hit>() {

    val TAG ="SearchHitsSourceLOG"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        return try {
//            val pageNumber = params.key ?: 1

            log(TAG, params.key.toString())

            val pageNumber = if(params.key == null || params.key == 0){
                1
            }else{
                params.key!!.plus(1)
            }

            val apiResponse = api.getHits(query = query, page = pageNumber, perPage = params.loadSize)
            val hits = apiResponse.hits

            var nextPageNumber: Int? = pageNumber.plus(1)
            if (hits.isNotEmpty()) {
                LoadResult.Page(
                    data = hits,
                    prevKey = pageNumber,
                    nextKey = nextPageNumber
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = nextPageNumber
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return state.anchorPosition
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
    }
}