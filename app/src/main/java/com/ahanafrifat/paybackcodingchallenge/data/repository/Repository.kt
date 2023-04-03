package com.ahanafrifat.paybackcodingchallenge.data.repository

import androidx.paging.PagingData
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.domain.repository.LocalDataSource
import com.ahanafrifat.paybackcodingchallenge.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {

    fun getAllHits(query: String?): Flow<PagingData<Hit>> {
        return remote.getHits(query)
    }

    suspend fun getSelectedHit(hitId: Int): Hit {
        return local.getHitById(hitId)
    }
}