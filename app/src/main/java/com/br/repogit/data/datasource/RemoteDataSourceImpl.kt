package com.br.repogit.data.datasource

import com.br.repogit.data.api.GitHubService
import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.utils.isNullOrEmpty
import com.br.repogit.utils.orEmpty
import com.br.repogit.utils.orFalse

private const val INITIAL_PAGE = 1
private const val EMPTY_LIST = 0L

class RemoteDataSourceImpl(
    private val api: GitHubService
) : RemoteDataSource {

    private var currentPage: Int = INITIAL_PAGE

    override suspend fun getRepositories(): RepositoriesResponse? {
        val response = api.getTopRepositories(page = currentPage)

        return if (response.isSuccessful) {
            checkBody(response.body())
        } else {
            null
        }
    }

    private fun checkBody(data: RepositoriesResponse?): RepositoriesResponse {
        return if (data.isNullOrEmpty() || data?.items?.isEmpty().orFalse()) {
            RepositoriesResponse(EMPTY_LIST, true, listOf())
        } else {
            currentPage++
            data.orEmpty()
        }
    }
}