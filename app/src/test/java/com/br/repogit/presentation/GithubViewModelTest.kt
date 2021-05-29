package com.br.repogit.presentation

import com.br.repogit.domain.usecase.GetRepositoriesUseCase
import com.br.repogit.presentation.mapper.GithubPresentation
import com.br.repogit.presentation.model.OwnerPresentation
import com.br.repogit.presentation.model.RepositoryPresentation
import com.br.repogit.utils.MainCoroutineRule
import com.br.repogit.utils.verify
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GithubViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val useCase: GetRepositoriesUseCase = mock()
    private val viewModel: GithubViewModel by lazy {
        GithubViewModel(useCase, Dispatchers.Unconfined)
    }

    @Test
    fun `get repositories when is first page should return list`() = runBlockingTest {
        // Given
        whenever(useCase.invoke(1)).thenReturn(mockResponseSuccess())

        // When
        viewModel.getRepositories()

        // Then
        verify(useCase).invoke(1)
        viewModel.loadingEvent.verify()

    }

    private fun mockResponseSuccess(): GithubPresentation {
        return GithubPresentation.SuccessResponse(
            listOf(
                RepositoryPresentation(
                    id = 1,
                    name = "teste",
                    fullName = "teste repo",
                    private = false,
                    owner = OwnerPresentation(
                        name = "My name",
                        avatarURL = "www.avatar.com"
                    ),
                    forksCount = 1111,
                    stargazersCount = 12,
                    totalCount = 241
                )
            )
        )
    }
}