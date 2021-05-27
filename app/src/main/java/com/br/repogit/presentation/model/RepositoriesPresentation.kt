package com.br.repogit.presentation.model

data class RepositoriesPresentation(
    val totalCount: Long,
    val incompleteResults: Boolean,
    val items: List<RepositoryPresentation>
)