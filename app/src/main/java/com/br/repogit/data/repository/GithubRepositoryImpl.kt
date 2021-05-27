package com.br.repogit.data.repository

import com.br.repogit.data.datasource.RemoteDataSource
import com.br.repogit.data.mapper.RepositoryDomainMapper
import com.br.repogit.domain.GithubDomainResponse
import com.br.repogit.domain.repository.GithubRepository

class GithubRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val mapper: RepositoryDomainMapper
) : GithubRepository {

    override suspend fun getRepositories(): GithubDomainResponse {
        return mapper.map(remoteDataSource.getRepositories())
    }
}