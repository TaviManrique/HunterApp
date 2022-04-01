package com.manriquetavi.hunterapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manriquetavi.hunterapp.util.Constants.HUNTER_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = HUNTER_REMOTE_KEYS_DATABASE_TABLE)
data class HunterRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
