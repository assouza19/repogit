package com.br.repogit.domain.usecase

import com.br.repogit.domain.repository.GithubRepository

class GetRepositoriesUseCase(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(currentPage : Int) = repository.getRepositories(currentPage)
}