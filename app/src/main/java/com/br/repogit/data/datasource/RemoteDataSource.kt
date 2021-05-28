package com.br.repogit.data.datasource

import com.br.repogit.data.model.RepositoriesResponse

interface RemoteDataSource {
    suspend fun getRepositories(currentPage: Int): RepositoriesResponse?
}