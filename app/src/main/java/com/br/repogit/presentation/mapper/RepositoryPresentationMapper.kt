package com.br.repogit.presentation.mapper

import com.br.repogit.domain.model.RepositoriesDomain
import com.br.repogit.presentation.model.RepositoriesPresentation
import com.br.repogit.utils.Mapper

class RepositoryPresentationMapper :
    Mapper<RepositoriesDomain, RepositoriesPresentation> {

    private val itemMapper = RepositoryItemPresentationMapper()

    override fun map(source: RepositoriesDomain): RepositoriesPresentation {
        return RepositoriesPresentation(
            totalCount = source.totalCount,
            incompleteResults = source.incompleteResults,
            items = itemMapper.map(source.items)
        )
    }
}