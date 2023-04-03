package com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_selected_hit

import com.ahanafrifat.paybackcodingchallenge.data.repository.Repository
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit

class GetSelectedHitUseCases(
    private val repository: Repository
) {

    suspend operator fun invoke(hitId: Int): Hit {
        return repository.getSelectedHit(hitId = hitId)
    }
}