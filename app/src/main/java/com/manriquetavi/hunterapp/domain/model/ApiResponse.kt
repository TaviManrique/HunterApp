package com.manriquetavi.hunterapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val hunters: List<Hunter> = emptyList(),
    val lastUpdated: Long? = null
)