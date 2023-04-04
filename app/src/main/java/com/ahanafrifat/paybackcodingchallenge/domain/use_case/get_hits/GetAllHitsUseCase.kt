package com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_hits

import androidx.paging.PagingData
import com.ahanafrifat.paybackcodingchallenge.data.repository.Repository
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllHitsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(query: String?): Flow<PagingData<Hit>> {
        return repository.getAllHits(query)
    }

}