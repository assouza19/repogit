package com.br.repogit.presentation.mapper

import com.br.repogit.domain.model.RepositoryDomain
import com.br.repogit.presentation.model.RepositoryPresentation
import com.br.repogit.utils.Mapper

class RepositoryPresentationMapper :
    Mapper<List<RepositoryDomain>?, GithubPresentation> {

    private val ownerMapper = OwnerPresentationMapper()

    override fun map(source: List<RepositoryDomain>?): GithubPresentation {
        return when {
            source == null -> {
                GithubPresentation.ErrorResponse
            }
            source.isEmpty() -> {
                GithubPresentation.EmptyResponse
            }
            else -> {
                toPresentation(source)
            }
        }
    }

    private fun toPresentation(source: List<RepositoryDomain>): GithubPresentation {
        return GithubPresentation.SuccessResponse(
            source.map {
                RepositoryPresentation(
                    id = it.id,
                    name = it.name.orEmpty(),
                    fullName = it.fullName.orEmpty(),
                    private = it.private ?: false,
                    owner = ownerMapper.map(it.owner),
                    description = it.description,
                    forksCount = it.forksCount ?: 0,
                    stargazersCount = it.stargazersCount ?: 0,
                    totalCount = it.totalCount ?: 0
                )
            }
        )
    }
}