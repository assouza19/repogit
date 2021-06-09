package com.br.repogit.data.repository

import com.br.repogit.data.datasource.RemoteDataSource
import com.br.repogit.domain.repository.GithubRepository
import com.br.repogit.presentation.mapper.GithubPresentation
import com.br.repogit.domain.mapper.RepositoryToPresentationMapper

class GithubRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : GithubRepository {

    private val mapper = RepositoryToPresentationMapper()

    override suspend fun getRepositories(currentPage: Int): GithubPresentation {
        return mapper.map(remoteDataSource.getRepositories(currentPage))
    }
}