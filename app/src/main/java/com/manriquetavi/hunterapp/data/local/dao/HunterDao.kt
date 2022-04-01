package com.manriquetavi.hunterapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manriquetavi.hunterapp.domain.model.Hunter

@Dao
interface HunterDao {

    @Query("SELECT * FROM hunter_table ORDER BY id ASC")
    fun getAllHunters(): PagingSource<Int, Hunter>

    @Query("SELECT * FROM hunter_table WHERE id=:hunterId")
    fun getSelectedHunter(hunterId: Int): Hunter

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHunters(hunters: List<Hunter>)

    @Query("DELETE FROM hunter_table")
    suspend fun deleteAllHunters()
}