package com.ahanafrifat.paybackcodingchallenge.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit

@Dao
interface HitsDao {

    @Query("SELECT * FROM hit_table ORDER BY id ASC")
    fun getAllHits(): PagingSource<Int, Hit>

    @Query("SELECT * FROM hit_table WHERE id=:id")
    fun getSelectedHits(id:Int): Hit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHits(hits: List<Hit>)

    @Query("DELETE FROM hit_table")
    suspend fun deleteAllHits()

}