package com.ahanafrifat.paybackcodingchallenge.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.HIT_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = HIT_REMOTE_KEYS_DATABASE_TABLE)
data class HitRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
