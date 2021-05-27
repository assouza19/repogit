package com.br.repogit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.repogit.domain.usecase.GetRepositoriesUseCase
import com.br.repogit.presentation.mapper.GithubPresentation
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GithubViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val dispatcher: CoroutineContext
): ViewModel() {

    fun getRepositories() {
        viewModelScope.launch{
            runCatching {
                withContext(dispatcher) {
                    getRepositoriesUseCase()
                }
            }.onSuccess {
                handlerSuccess(it)
            }.onFailure {

            }
        }
    }

    private fun handlerSuccess(data: GithubPresentation) {
        when(data) {
            GithubPresentation.EmptyResponse -> TODO()
            GithubPresentation.ErrorResponse -> TODO()
            is GithubPresentation.SuccessResponse -> TODO()
        }
    }
}