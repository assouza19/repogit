package com.br.repogit.data.datasource

import android.util.Log
import com.br.repogit.data.api.GitHubService
import com.br.repogit.data.mapper.RepositoryToDomainMapper
import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.domain.model.RepositoryDomain
import com.br.repogit.utils.Extensions


class RemoteDataSourceImpl(
    private val service: GitHubService
) : RemoteDataSource {

    private val mapper = RepositoryToDomainMapper()

    override suspend fun getRepositories(currentPage: Int): List<RepositoryDomain>? {
        val response = service.getTopRepositories(currentPage = currentPage)

        return if (response.isSuccessful) {
            checkBody(response.body())
        } else {
            Log.d("erro no request código", response.code().toString())
            null
        }
    }

    private fun checkBody(data: RepositoriesResponse?): List<RepositoryDomain> {
        return if (Extensions.isNullOrEmpty(data) || data?.items.isNullOrEmpty()) {
            listOf()
        } else {
            mapper.map(Extensions.orEmpty(data))
        }
    }
}