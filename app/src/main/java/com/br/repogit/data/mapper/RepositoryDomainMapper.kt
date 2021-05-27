package com.br.repogit.data.mapper

import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.domain.GithubDomainResponse
import com.br.repogit.domain.model.RepositoriesDomain
import com.br.repogit.utils.Mapper

class RepositoryDomainMapper :
    Mapper<RepositoriesResponse?, GithubDomainResponse> {

    private val itemMapper = RepositoryItemDomainMapper()

    override fun map(source: RepositoriesResponse?): GithubDomainResponse {
        return if (source == null) {
            GithubDomainResponse.ErrorResponse
        } else {
            if (source.items.isEmpty()) {
                GithubDomainResponse.EmptyResponse
            } else {
                source.asDomain(source)
            }
        }
    }

    private fun RepositoriesResponse.asDomain(list: RepositoriesResponse) =
        GithubDomainResponse.SuccessResponse(
            RepositoriesDomain(
                totalCount = list.totalCount,
                incompleteResults = list.incompleteResults,
                items = itemMapper.map(list.items)
            )
        )
}