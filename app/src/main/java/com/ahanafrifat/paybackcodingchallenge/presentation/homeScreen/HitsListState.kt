package com.ahanafrifat.paybackcodingchallenge.presentation.homeScreen

import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit

data class HitsListState(
    val isLoading: Boolean = false,
    val hits: List<Hit> = emptyList(),
    val error: String = ""
)
