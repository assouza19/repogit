package com.br.repogit.presentation.mapper

import com.br.repogit.presentation.model.RepositoryPresentation

sealed class GithubPresentation {
    object ErrorResponse : GithubPresentation()
    object EmptyResponse : GithubPresentation()
    data class SuccessResponse(val items: List<RepositoryPresentation>) : GithubPresentation()
}