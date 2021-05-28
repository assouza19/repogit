package com.br.repogit.data.datasource

import com.br.repogit.data.api.RetrofitClient
import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.utils.isNullOrEmpty
import com.br.repogit.utils.orEmpty
import com.br.repogit.utils.orFalse

private const val EMPTY_LIST = 0L

class RemoteDataSourceImpl(
    private val retrofit: RetrofitClient
) : RemoteDataSource {

    override suspend fun getRepositories(currentPage: Int): RepositoriesResponse? {
        val response = retrofit.newInstance().getTopRepositories(currentPage = currentPage)

        return if (response.isSuccessful) {
            checkBody(response.body())
        } else {
            null
        }
    }

    private fun checkBody(data: RepositoriesResponse?): RepositoriesResponse {
        return if (data.isNullOrEmpty() || data?.items.isNullOrEmpty()) {
            RepositoriesResponse(EMPTY_LIST, true, listOf())
        } else {
            data.orEmpty()
        }
    }
}