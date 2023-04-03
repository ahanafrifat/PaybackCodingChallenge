package com.ahanafrifat.paybackcodingchallenge.domain.repository

import androidx.paging.PagingData
import com.ahanafrifat.paybackcodingchallenge.domain.model.ApiResponse
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getHits(query: String?): Flow<PagingData<Hit>>

}