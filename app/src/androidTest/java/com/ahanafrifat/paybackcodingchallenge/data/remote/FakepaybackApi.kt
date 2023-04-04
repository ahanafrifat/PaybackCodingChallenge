package com.ahanafrifat.paybackcodingchallenge.data.remote

import com.ahanafrifat.paybackcodingchallenge.domain.model.ApiResponse
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit

class FakepaybackApi : PaybackApi {

    override suspend fun getHits(
        apiKey: String?,
        query: String?,
        page: Int?,
        perPage: Int?
    ): ApiResponse {
        TODO("Not yet implemented")
    }
}