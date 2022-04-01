package com.manriquetavi.hunterapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manriquetavi.hunterapp.util.Constants.HUNTER_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = HUNTER_DATABASE_TABLE)
data class Hunter(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val nenTypes: List<String>
)
