package com.br.repogit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.repogit.domain.usecase.GetRepositoriesUseCase
import com.br.repogit.presentation.mapper.GithubPresentation
import com.br.repogit.presentation.model.RepositoryPresentation
import com.br.repogit.utils.Event
import com.br.repogit.utils.SimpleEvent
import com.br.repogit.utils.triggerEvent
import com.br.repogit.utils.triggerPostEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GithubViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val dispatcher: CoroutineContext
) : ViewModel() {

    private val _emptyResponseEvent = MutableLiveData<SimpleEvent>()
    private val _errorResponseEvent = MutableLiveData<SimpleEvent>()
    private val _showLoadingEvent = MutableLiveData<SimpleEvent>()
    private val _successResponseEvent = MutableLiveData<Event<List<RepositoryPresentation>>>()

    val emptyResponseEvent: LiveData<SimpleEvent>
        get() = _emptyResponseEvent
    val errorResponseEvent: LiveData<SimpleEvent>
        get() = _errorResponseEvent
    val successResponseEvent: LiveData<Event<List<RepositoryPresentation>>>
        get() = _successResponseEvent
    val loadingEvent: LiveData<SimpleEvent>
        get() = _showLoadingEvent

    fun getRepositories() {
        viewModelScope.launch {
            _showLoadingEvent.triggerEvent()

            withContext(dispatcher) {
                runCatching {

                    /* Delay abaixo completamente desnecessário, somente
                    *  para ver a animação do lottie =)
                    *  Em um app real, nunca faria isso :hehe
                     */

                    delay(1500L)
                    getRepositoriesUseCase()
                }.onSuccess {
                    handlerSuccess(it)
                }.onFailure {
                    handleFailure()
                }
            }
        }
    }

    private fun handleFailure() {
        _errorResponseEvent.triggerEvent()
    }

    private fun handlerSuccess(data: GithubPresentation) {
        when (data) {
            is GithubPresentation.EmptyResponse -> _emptyResponseEvent.triggerEvent()
            is GithubPresentation.ErrorResponse -> _errorResponseEvent.triggerEvent()
            is GithubPresentation.SuccessResponse -> _successResponseEvent.triggerPostEvent(data.items.items)
        }
    }
}