package com.example.gramoday.repository

import com.example.gramoday.data.api.ApiService
import com.example.gramoday.data.models.NetworkResponse
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) : ApiService {
    override suspend fun getNetworkResponse(): NetworkResponse = apiService.getNetworkResponse()
}