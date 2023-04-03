package com.ahanafrifat.paybackcodingchallenge.data.paging_source

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ahanafrifat.paybackcodingchallenge.data.local.PayBackDatabase
import com.ahanafrifat.paybackcodingchallenge.data.remote.PaybackApi
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.domain.model.HitRemoteKeys
import com.ahanafrifat.paybackcodingchallenge.utils.*
import javax.inject.Inject

@ExperimentalPagingApi
class HitRemoteMediator @Inject constructor(
    private val paybackApi: PaybackApi,
    private val paybackDatabase: PayBackDatabase,
    private val query: String?,
    private val context: Context
) : RemoteMediator<Int, Hit>() {

    val TAG = "HitRemoteMediatorTAG"
    private val hitsDao = paybackDatabase.hitDao()
    private val hitRemoteKeysDao = paybackDatabase.hitRemoteKeysDao()
    private var saveQuery:String =""


    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = if(hitRemoteKeysDao.getAllRemoteKeys().isNotEmpty()){
            hitRemoteKeysDao.getAllRemoteKeys()?.sortedBy { it.nextPage }?.last()?.lastUpdated
        }else{
            0L
        }
        val cacheTimeout = 10

        val diffInMinutes = (currentTime - lastUpdated!!) / 1000 / 60
        return if (!query.equals(saveQuery)){
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
        else if(!checkForInternet(context)){
            InitializeAction.SKIP_INITIAL_REFRESH
        }
        else if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
        else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hit>): MediatorResult {
        return try {
            log(TAG, "load started")

            if (!checkForInternet(context)) {
                return MediatorResult.Success(endOfPaginationReached = false)
            }

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    val nextPage = remoteKeys?.nextPage?.minus(1) ?: 1
                    log(TAG, "REFRESH remoteKeys : $remoteKeys nextPage: $nextPage")
                    nextPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    log(TAG, "PREPEND remoteKeys : $remoteKeys prevPage: $prevPage")
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKey =
                        hitRemoteKeysDao.getAllRemoteKeys()?.sortedBy { it.nextPage }?.last()
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKey != null
                        )
                    log(TAG, "APPEND remoteKeys : $remoteKey nextPage: $nextPage")
                    nextPage
                }
            }

            log(TAG, "page: $page pageSize: ${state.config.pageSize}")
            query?.let { saveQuery = it }
            val response =
                paybackApi.getHits(page = page, query = query, perPage = state.config.pageSize)
            if (response.hits.isNotEmpty()) {
                paybackDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        hitsDao.deleteAllHits()
                        hitRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val prevPage = page
                    val nextPage = page.plus(1)
                    val currentTime = System.currentTimeMillis()

                    val keys = response.hits.map { hit ->
                        HitRemoteKeys(
                            id = hit.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = currentTime
                        )
                    }.sortedBy { it.nextPage }

                    log(TAG, "keys: $keys")

                    val isHitExist = hitRemoteKeysDao.isHitExist(itemId = keys.last().id)

                    log(TAG, "isHitExist ${isHitExist}")

                    if (!isHitExist) {
                        hitRemoteKeysDao.addAllRemoteKeys(hitRemoteKeys = keys)
                        hitsDao.addHits(hits = response.hits)
                    }

                }
            }
            MediatorResult.Success(endOfPaginationReached = !response.message.isNullOrBlank())
        } catch (e: Exception) {
            log(TAG, "load Exception ${e.message}")
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Hit>
    ): HitRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                log("RemoteKey", "getRemoteKeyClosestToCurrentPosition() hitId: ${id}")
                hitRemoteKeysDao.getRemoteKeys(hitId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Hit>
    ): HitRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hit ->
                log("RemoteKey", "getRemoteKeyForFirstItem() hitId: ${hit.id}")
                hitRemoteKeysDao.getRemoteKeys(hitId = hit.id)
            }
    }
}