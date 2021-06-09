package com.br.repogit.domain

import com.br.repogit.domain.repository.GithubRepository
import com.br.repogit.domain.usecase.GetRepositoriesUseCase
import com.br.repogit.presentation.mapper.GithubPresentation
import com.br.repogit.presentation.model.OwnerPresentation
import com.br.repogit.presentation.model.RepositoryPresentation
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetRepositoriesUseCaseTest {

    private val repository: GithubRepository = mock()
    private val useCase: GetRepositoriesUseCase = GetRepositoriesUseCase(repository)

    @Test
    fun `when invoke should return list`() = runBlockingTest {
        // Given
        whenever(repository.getRepositories(1)).thenReturn(mockResponse())

        // When
        val result = useCase.invoke(1)

        // Then
        assertEquals(result, mockResponse())
    }

    @Test
    fun `when invoke should return empty list`() = runBlockingTest {
        // Given
        whenever(repository.getRepositories(1)).thenReturn(mockEmptyResponse())

        // When
        val result = useCase.invoke(1)

        // Then
        assertEquals(result, GithubPresentation.EmptyResponse)
    }

    @Test
    fun `when invoke should return throwable`() = runBlockingTest {
        // Given
        whenever(repository.getRepositories(1)).thenReturn(GithubPresentation.ErrorResponse)

        // When
        val result = useCase.invoke(1)

        // Then
        assertEquals(result, GithubPresentation.ErrorResponse)
    }

    private fun mockResponse() =
        GithubPresentation.SuccessResponse(
            listOf(
                RepositoryPresentation(
                    id = 12,
                    private = false,
                    owner = OwnerPresentation("", ""),
                    name = "",
                    fullName = "",
                    forksCount = 0,
                    stargazersCount = 0,
                    totalCount = 12
                )
            )
        )

    private fun mockEmptyResponse() = GithubPresentation.EmptyResponse
}