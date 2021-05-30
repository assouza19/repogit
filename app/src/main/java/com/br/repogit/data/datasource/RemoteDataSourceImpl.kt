package com.br.repogit.data.datasource

import com.br.repogit.data.api.GitHubService
import com.br.repogit.data.mapper.RepositoryToDomainMapper
import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.domain.model.RepositoryDomain
import com.br.repogit.utils.isNullOrEmpty
import com.br.repogit.utils.orEmpty

class RemoteDataSourceImpl(
    private val service: GitHubService
) : RemoteDataSource {

    private val mapper = RepositoryToDomainMapper()

    override suspend fun getRepositories(currentPage: Int): List<RepositoryDomain>? {
        val response = service.getTopRepositories(currentPage = currentPage)

        return if (response.isSuccessful) {
            checkBody(response.body())
        } else {
            null
        }
    }

    private fun checkBody(data: RepositoriesResponse?): List<RepositoryDomain> {
        return if (data.isNullOrEmpty() || data?.items.isNullOrEmpty()) {
            listOf()
        } else {
            mapper.map(data.orEmpty())
        }
    }
}