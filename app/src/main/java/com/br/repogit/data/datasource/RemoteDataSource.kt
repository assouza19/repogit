package com.br.repogit.data.datasource

import com.br.repogit.domain.model.RepositoryDomain

interface RemoteDataSource {
    suspend fun getRepositories(currentPage: Int): List<RepositoryDomain>?
}