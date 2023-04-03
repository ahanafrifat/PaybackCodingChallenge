package com.ahanafrifat.paybackcodingchallenge.domain.use_case

import com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_hits.GetAllHitsUseCase
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_selected_hit.GetSelectedHitUseCases

data class UseCases(
    val getAllHitsUseCase: GetAllHitsUseCase,
    val getSelectedHitUseCases: GetSelectedHitUseCases
)
