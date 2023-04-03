package com.ahanafrifat.paybackcodingchallenge.data.remote

import com.ahanafrifat.paybackcodingchallenge.domain.model.ApiResponse
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.API_KEY
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.ITEMS_PER_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface PaybackApi {

    @GET("api/")
    suspend fun getHits(
        @Query("key") apiKey: String? = API_KEY,
        @Query("q") query: String? = "",
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = ITEMS_PER_PAGE
    ): ApiResponse

}