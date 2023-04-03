package com.ahanafrifat.paybackcodingchallenge.data.repository

import com.ahanafrifat.paybackcodingchallenge.data.local.PayBackDatabase
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.domain.repository.LocalDataSource

class LocalDataSourceImpl(hitDatabase: PayBackDatabase):LocalDataSource {

    private val hitDao = hitDatabase.hitDao()

    override suspend fun getHitById(hitId: Int): Hit {
        return hitDao.getSelectedHits(id= hitId)
    }
}