package com.ahanafrifat.paybackcodingchallenge.domain.use_case

import com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_hits.GetAllHitsUseCase
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_selected_hit.GetSelectedHitUseCases
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.search_hits.SearchHitsUseCase

data class UseCases(
    val getAllHitsUseCase: GetAllHitsUseCase,
    val searchHitsUseCase: SearchHitsUseCase,
    val getSelectedHitUseCases: GetSelectedHitUseCases
)
