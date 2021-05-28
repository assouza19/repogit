package com.br.repogit.domain.model

data class RepositoryDomain(
    val id: Int,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: OwnerDomain,
    val description: String? = null,
    val forksCount: Int,
    val stargazersCount: Int
)