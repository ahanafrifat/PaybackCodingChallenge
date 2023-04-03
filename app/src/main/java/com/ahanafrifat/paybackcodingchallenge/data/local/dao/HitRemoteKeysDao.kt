package com.ahanafrifat.paybackcodingchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahanafrifat.paybackcodingchallenge.domain.model.HitRemoteKeys

@Dao
interface HitRemoteKeysDao {

    @Query("SELECT * FROM hit_remote_keys_table WHERE id = :hitId")
    suspend fun getRemoteKeys(hitId: Int): HitRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(hitRemoteKeys: List<HitRemoteKeys>)

    @Query("DELETE FROM hit_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

    @Query("SELECT * FROM hit_remote_keys_table")
    suspend fun getAllRemoteKeys(): List<HitRemoteKeys>

    @Query("SELECT COUNT(*) FROM hit_remote_keys_table WHERE id = :itemId")
    suspend fun isHitExist(itemId: Int): Boolean

}