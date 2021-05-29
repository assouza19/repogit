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

private const val INITIAL_PAGE = 1
private const val ZERO = 0

internal class GithubViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val dispatcher: CoroutineContext
) : ViewModel() {

    private var currentPage: Int = INITIAL_PAGE
    private var totalResult: Int = ZERO
    private var currentList: List<RepositoryPresentation> = emptyList()

    private val _emptyResponseEvent = MutableLiveData<SimpleEvent>()
    private val _fullResultResponseEvent = MutableLiveData<SimpleEvent>()
    private val _errorResponseEvent = MutableLiveData<SimpleEvent>()
    private val _showLoadingEvent = MutableLiveData<SimpleEvent>()
    private val _showScrollLoadingEvent = MutableLiveData<SimpleEvent>()
    private val _showToast = MutableLiveData<SimpleEvent>()
    private val _successResponseEvent = MutableLiveData<Event<List<RepositoryPresentation>>>()

    val emptyResponseEvent: LiveData<SimpleEvent>
        get() = _emptyResponseEvent
    val fullResultResponseEvent: LiveData<SimpleEvent>
        get() = _fullResultResponseEvent
    val errorResponseEvent: LiveData<SimpleEvent>
        get() = _errorResponseEvent
    val successResponseEvent: LiveData<Event<List<RepositoryPresentation>>>
        get() = _successResponseEvent
    val loadingEvent: LiveData<SimpleEvent>
        get() = _showLoadingEvent
    val scrollLoadingEvent: LiveData<SimpleEvent>
        get() = _showScrollLoadingEvent
    val showToast: LiveData<SimpleEvent>
        get() = _showToast

    fun getRepositories(currentPage: Int = INITIAL_PAGE, isScrolling: Boolean = false) {
        if (currentList.isNullOrEmpty() || isScrolling) {
            requestList(currentPage, isScrolling)
        } else {
            _successResponseEvent.triggerEvent(currentList)
        }
    }

    private fun requestList(currentPage: Int, isScrolling: Boolean) {
        viewModelScope.launch {
            handlerLoading(isScrolling)

            withContext(dispatcher) {
                runCatching {

                    /* Delay abaixo completamente desnecessário, somente
                    *  para ver a animação do lottie =)
                    *  Em um app real, nunca faria isso :hehe
                     */

                    delay(1300L)
                    getRepositoriesUseCase(currentPage)
                }.onSuccess {
                    if (isScrolling) handlerNextPageSuccess(it)
                    else handlerSuccess(it)
                }.onFailure {
                    if (isScrolling) {
                        _showToast.triggerEvent()
                    } else {
                        _errorResponseEvent.triggerEvent()
                    }
                }
            }
        }
    }

    private fun handlerLoading(isScrolling: Boolean) {
        if (isScrolling) {
            _showScrollLoadingEvent.triggerEvent()
        } else {
            _showLoadingEvent.triggerEvent()
        }
    }

    fun nextPage(samePage: Boolean = false) {
        if (currentList.size < totalResult) {
            if (!samePage) currentPage++
            getRepositories(currentPage, true)
        } else {
            _fullResultResponseEvent.triggerEvent()
        }
    }

    private fun handlerNextPageSuccess(data: GithubPresentation) {
        when (data) {
            is GithubPresentation.EmptyResponse -> _fullResultResponseEvent.triggerEvent()
            is GithubPresentation.ErrorResponse -> _showToast.triggerEvent()
            is GithubPresentation.SuccessResponse -> {
                currentList = currentList.subList(ZERO, currentList.size - 1) + data.items
                _successResponseEvent.triggerPostEvent(currentList)
            }
        }
    }

    private fun handlerSuccess(data: GithubPresentation) {
        when (data) {
            is GithubPresentation.EmptyResponse -> _emptyResponseEvent.triggerEvent()
            is GithubPresentation.ErrorResponse -> _errorResponseEvent.triggerEvent()
            is GithubPresentation.SuccessResponse -> {
                totalResult = data.items[0].totalCount
                currentList = data.items
                _successResponseEvent.triggerPostEvent(currentList)
            }
        }
    }
}