package com.br.repogit.domain.repository

import com.br.repogit.domain.GithubDomainResponse

interface GithubRepository {
    suspend fun getRepositories(): GithubDomainResponse
}