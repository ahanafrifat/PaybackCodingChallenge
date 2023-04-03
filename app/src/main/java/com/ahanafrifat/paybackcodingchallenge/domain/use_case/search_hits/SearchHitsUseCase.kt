package com.ahanafrifat.paybackcodingchallenge.domain.use_case.search_hits

import androidx.paging.PagingData
import com.ahanafrifat.paybackcodingchallenge.data.repository.Repository
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import kotlinx.coroutines.flow.Flow

class SearchHitsUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String):Flow<PagingData<Hit>> {
        return repository.searchHits(query = query)
    }
}