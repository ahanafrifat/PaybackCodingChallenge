package com.ahanafrifat.paybackcodingchallenge.domain.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("hits")
    val hits: List<Hit> = emptyList(),
    @SerializedName("total")
    val total: Int? = 0,
    @SerializedName("totalHits")
    val totalHits: Int? = 0,
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("message")
    val message: String? = null
)