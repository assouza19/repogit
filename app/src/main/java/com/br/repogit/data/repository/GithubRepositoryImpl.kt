package com.br.repogit.data.repository

import com.br.repogit.data.datasource.RemoteDataSource
import com.br.repogit.data.mapper.RepositoryDomainMapper
import com.br.repogit.presentation.mapper.GithubPresentation
import com.br.repogit.domain.repository.GithubRepository

class GithubRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val mapper: RepositoryDomainMapper
) : GithubRepository {

    override suspend fun getRepositories(): GithubPresentation {
        return mapper.map(remoteDataSource.getRepositories())
    }
}