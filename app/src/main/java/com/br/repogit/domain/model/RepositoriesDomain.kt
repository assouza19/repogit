package com.br.repogit.domain.model

data class RepositoriesDomain(
    val totalCount: Long,
    val incompleteResults: Boolean,
    val items: List<RepositoryDomain>
)