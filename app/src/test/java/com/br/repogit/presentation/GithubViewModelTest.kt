package com.br.repogit.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.br.repogit.domain.usecase.GetRepositoriesUseCase
import com.br.repogit.presentation.mapper.GithubPresentation
import com.br.repogit.presentation.model.OwnerPresentation
import com.br.repogit.presentation.model.RepositoryPresentation
import com.br.repogit.utils.MainCoroutineRule
import com.br.repogit.utils.await
import com.br.repogit.utils.verify
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GithubViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val useCase: GetRepositoriesUseCase = mock()
    private val viewModel: GithubViewModel by lazy {
        GithubViewModel(useCase, Dispatchers.Default)
    }

    @Test
    fun `get repositories when is first page should return list`() = runBlockingTest {
        // Given
        whenever(useCase.invoke(any())).thenReturn(mockResponseSuccess())

        // When
        viewModel.getRepositories()

        // Then
        verify(useCase).invoke(eq(1))
        viewModel.loadingEvent.verify()
        viewModel.successResponseEvent.verify {
            assertEquals(241, it!!.data[0].totalCount)
            assertEquals(1, it.data[0].id)
            assertEquals("teste", it.data[0].name)
            assertEquals("teste repo", it.data[0].fullName)
            assertEquals("My name", it.data[0].owner.name)
            assertEquals("www.avatar.com", it.data[0].owner.avatarURL)
        }
    }

    @Test
    fun `get repositories when is the last page should return empty list`() = runBlockingTest {
        // Given
        whenever(useCase.invoke(1)).thenReturn(mockResponseSuccess())
        whenever(useCase.invoke(2)).thenReturn(mockResponseEmptySuccess())

        // When
        viewModel.getRepositories()
        viewModel.successResponseEvent.await(50L)
        viewModel.nextPage(samePage = false)

        // Then
        verify(useCase, times(2)).invoke(any())
        viewModel.loadingEvent.verify()
        viewModel.scrollLoadingEvent.verify()
        viewModel.fullResultResponseEvent.verify()
    }

    @Test
    fun `get repositories when is the last page should return error`() = runBlockingTest {
        // Given
        whenever(useCase.invoke(1)).thenReturn(mockResponseSuccess())
        whenever(useCase.invoke(2)).thenReturn(GithubPresentation.ErrorResponse)

        // When
        viewModel.getRepositories()
        viewModel.successResponseEvent.await(50L)
        viewModel.nextPage(samePage = false)

        // Then
        verify(useCase, times(2)).invoke(any())
        viewModel.loadingEvent.verify()
        viewModel.scrollLoadingEvent.verify()
        viewModel.showToast.verify()
    }

    @Test
    fun `get repositories when already load and not scrolling should not call use case`() =
        runBlockingTest {
            // Given
            whenever(useCase.invoke(1)).thenReturn(mockResponseSuccess())

            // When
            viewModel.getRepositories()
            viewModel.successResponseEvent.await(50L)
            viewModel.getRepositories()

            // Then
            verify(useCase).invoke(eq(1))
            viewModel.successResponseEvent.verify()
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

    private fun mockResponseNexPageSuccess(): GithubPresentation {
        return GithubPresentation.SuccessResponse(
            listOf(
                RepositoryPresentation(
                    id = 2,
                    name = "teste2",
                    fullName = "teste repo2",
                    private = false,
                    owner = OwnerPresentation(
                        name = "My name2",
                        avatarURL = "www.avatar.com"
                    ),
                    forksCount = 1111,
                    stargazersCount = 12,
                    totalCount = 241
                )
            )
        )
    }

    private fun mockResponseEmptySuccess() = GithubPresentation.EmptyResponse
}