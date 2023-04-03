package com.ahanafrifat.paybackcodingchallenge.domain.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("message")
    val message: String? = null
)