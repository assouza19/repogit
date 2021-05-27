package com.br.repogit.domain.model

data class RepositoryDomain(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: OwnerDomain,
    val description: String? = null,
    val forksCount: Long,
    val stargazersCount: Long
)