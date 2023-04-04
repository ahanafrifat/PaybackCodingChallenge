package com.ahanafrifat.paybackcodingchallenge.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahanafrifat.paybackcodingchallenge.data.local.PayBackDatabase
import com.ahanafrifat.paybackcodingchallenge.data.paging_source.HitRemoteMediator
import com.ahanafrifat.paybackcodingchallenge.data.remote.PaybackApi
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.domain.repository.RemoteDataSource
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class RemoteDataSourceImpl @Inject constructor(
    private val context: Context,
    private val paybackApi: PaybackApi,
    private val paybackDatabase: PayBackDatabase
) : RemoteDataSource {

    private val hitsDao = paybackDatabase.hitDao()

    override fun getHits(query: String?): Flow<PagingData<Hit>> {
        val pagingSourceFactory = { hitsDao.getAllHits() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HitRemoteMediator(
                paybackApi = paybackApi,
                paybackDatabase = paybackDatabase,
                query = query,
                context = context
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}