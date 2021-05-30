package com.br.repogit.data.mapper

import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.domain.model.RepositoryDomain
import com.br.repogit.utils.Mapper

class RepositoryToDomainMapper :
    Mapper<RepositoriesResponse, List<RepositoryDomain>> {

    private val ownerMapper = OwnerToDomainMapper()

    override fun map(source: RepositoriesResponse): List<RepositoryDomain> {
        return source.items.map {
            RepositoryDomain(
                id = it.id,
                name = it.name,
                fullName = it.fullName,
                private = it.private,
                owner = ownerMapper.map(it.owner),
                description = it.description,
                forksCount = it.forksCount,
                stargazersCount = it.stargazersCount,
                totalCount = source.totalCount.toInt()
            )
        }
    }
}