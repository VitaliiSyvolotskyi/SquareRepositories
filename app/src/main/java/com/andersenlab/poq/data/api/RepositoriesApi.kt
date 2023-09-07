package com.andersenlab.poq.data.api

import com.andersenlab.poq.data.model.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface RepositoriesApi {

    @GET("orgs/square/repos")
    suspend fun getRepositories(): Response<List<RepositoryResponse>>
}