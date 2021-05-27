package com.br.repogit.data.mapper

import com.br.repogit.data.model.Repository
import com.br.repogit.domain.model.RepositoryDomain
import com.br.repogit.utils.Mapper

class RepositoryItemDomainMapper : Mapper<List<Repository>, List<RepositoryDomain>> {

    private val ownerMapper = OwnerDomainMapper()

    override fun map(source: List<Repository>): List<RepositoryDomain> {
        return source.map {
            RepositoryDomain(
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