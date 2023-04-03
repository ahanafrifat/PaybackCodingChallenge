package com.ahanafrifat.paybackcodingchallenge.domain.repository

import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit

interface LocalDataSource {

    suspend fun getHitById(hitId: Int): Hit

}