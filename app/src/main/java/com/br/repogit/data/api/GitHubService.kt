package com.br.repogit.data.api

import com.br.repogit.data.model.RepositoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("/search/repositories")
    suspend fun getTopRepositories(
        @Query("q") query: String = "language:kotlin",
        @Query("sort") sortBy: String = "stars",
        @Query("page") currentPage: Int
    ): Response<RepositoriesResponse>
}