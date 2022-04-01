package com.manriquetavi.hunterapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manriquetavi.hunterapp.domain.model.HunterRemoteKeys

@Dao
interface HunterRemoteKeysDao {

    @Query("SELECT * FROM hunter_remote_keys_table WHERE id =:hunterId")
    suspend fun getRemoteKeys(hunterId: Int): HunterRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(hunterRemoteKeys: List<HunterRemoteKeys>)

    @Query("DELETE FROM hunter_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}