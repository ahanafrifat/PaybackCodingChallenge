package com.ahanafrifat.paybackcodingchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahanafrifat.paybackcodingchallenge.data.local.dao.HitRemoteKeysDao
import com.ahanafrifat.paybackcodingchallenge.data.local.dao.HitsDao
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.domain.model.HitRemoteKeys

@Database(entities = [Hit::class, HitRemoteKeys::class], version =1)
abstract class PayBackDatabase: RoomDatabase() {

    abstract fun hitDao(): HitsDao

    abstract fun hitRemoteKeysDao(): HitRemoteKeysDao
}