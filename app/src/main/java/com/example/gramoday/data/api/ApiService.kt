package com.example.gramoday.data.api

import com.example.gramoday.data.models.NetworkResponse
import retrofit2.http.GET

interface ApiService {

    @GET("user/bySlug?profileSlug=mmtradingco")
    suspend fun getNetworkResponse(): NetworkResponse

}