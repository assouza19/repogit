package com.br.repogit.presentation.model

data class RepositoryPresentation(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: OwnerPresentation,
    val description: String? = null,
    val forksCount: Long,
    val stargazersCount: Long
)