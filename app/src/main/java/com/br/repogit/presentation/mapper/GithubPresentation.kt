package com.br.repogit.presentation.mapper

import com.br.repogit.presentation.model.RepositoriesPresentation

sealed class GithubPresentation {
    object ErrorResponse : GithubPresentation()
    object EmptyResponse : GithubPresentation()
    class SuccessResponse(val data: RepositoriesPresentation) : GithubPresentation()
}
