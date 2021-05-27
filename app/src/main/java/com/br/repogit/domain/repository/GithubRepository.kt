package com.br.repogit.domain.repository

import com.br.repogit.presentation.mapper.GithubPresentation

interface GithubRepository {
    suspend fun getRepositories(): GithubPresentation
}