package com.br.repogit.data.datasource

import com.br.repogit.data.api.GitHubService
import com.br.repogit.data.model.RepositoriesResponse

private const val INITIAL_PAGE = 1

class RemoteDataSourceImpl(
    private val api: GitHubService
) : RemoteDataSource {

    private var currentPage: Int = INITIAL_PAGE

    override suspend fun getRepositories(): RepositoriesResponse? {
        val response = api.getTopRepositories(page = currentPage)

        return if (response.isSuccessful) {
            response.body()?.let {
                currentPage++
                it
            }

            RepositoriesResponse(0, true, listOf())
        } else {
            null
        }
    }
}