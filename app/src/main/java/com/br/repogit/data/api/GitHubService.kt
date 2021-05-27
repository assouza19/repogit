package com.br.repogit.data.api

import com.br.repogit.data.model.RepositoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("/search/repositories")
    fun getTopRepositories(
        @Query("language") language : String = "kotlin",
        @Query("sort") sortBy : String = "stars",
        @Query("page") page: Int
    ): Response<RepositoriesResponse>
}