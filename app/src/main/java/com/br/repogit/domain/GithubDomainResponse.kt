package com.br.repogit.domain

import com.br.repogit.domain.model.RepositoriesDomain

sealed class GithubDomainResponse {
    object ErrorResponse: GithubDomainResponse()
    object EmptyResponse: GithubDomainResponse()
    class SuccessResponse(val data: RepositoriesDomain) : GithubDomainResponse()
}
