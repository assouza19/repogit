package com.br.repogit.data.mapper

import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.presentation.mapper.GithubPresentation
import com.br.repogit.presentation.mapper.RepositoryItemPresentationMapper
import com.br.repogit.presentation.model.RepositoriesPresentation
import com.br.repogit.utils.Mapper

class RepositoryDomainMapper :
    Mapper<RepositoriesResponse?, GithubPresentation> {

    private val itemMapper = RepositoryItemDomainMapper()
    private val itemMapperPresentation = RepositoryItemPresentationMapper()

    override fun map(source: RepositoriesResponse?): GithubPresentation {
        return if (source == null) {
            GithubPresentation.ErrorResponse
        } else {
            if (source.items.isEmpty()) {
                GithubPresentation.EmptyResponse
            } else {
                source.asPresentation(source)
            }
        }
    }

    private fun RepositoriesResponse.asPresentation(list: RepositoriesResponse) =
        GithubPresentation.SuccessResponse(
            RepositoriesPresentation(
                totalCount = list.totalCount,
                incompleteResults = list.incompleteResults,
                items = itemMapperPresentation.map(itemMapper.map(list.items))
            )
        )
}