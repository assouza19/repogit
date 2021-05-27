package com.br.repogit.presentation.mapper

import com.br.repogit.domain.model.RepositoryDomain
import com.br.repogit.presentation.model.RepositoryPresentation
import com.br.repogit.utils.Mapper

class RepositoryItemPresentationMapper :
    Mapper<List<RepositoryDomain>, List<RepositoryPresentation>> {

    private val ownerMapper = OwnerPresentationMapper()

    override fun map(source: List<RepositoryDomain>): List<RepositoryPresentation> {
        return source.map {
            RepositoryPresentation(
                id = it.id,
                name = it.name,
                fullName = it.fullName,
                private = it.private,
                owner = ownerMapper.map(it.owner),
                description = it.description,
                stargazersCount = it.stargazersCount,
                forksCount = it.forksCount
            )
        }
    }
}