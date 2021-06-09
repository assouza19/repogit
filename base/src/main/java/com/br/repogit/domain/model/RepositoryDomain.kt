package com.br.repogit.domain.model

data class RepositoryDomain(
    val id: Int,
    val name: String? = "",
    val fullName: String? = "",
    val private: Boolean? = false,
    val owner: OwnerDomain? = OwnerDomain(),
    val description: String? = null,
    val forksCount: Int? = 0,
    val stargazersCount: Int? = 0,
    val totalCount: Int? = 0
)