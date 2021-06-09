package com.br.repogit.data.repository

import com.br.repogit.data.datasource.RemoteDataSource
import com.br.repogit.domain.model.OwnerDomain
import com.br.repogit.domain.model.RepositoryDomain
import com.br.repogit.domain.repository.GithubRepository
import com.br.repogit.presentation.mapper.GithubPresentation
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GithubRepositoryTest {

    private val dataSource: RemoteDataSource = mock()

    private val repository: GithubRepository by lazy {
        GithubRepositoryImpl(dataSource)
    }

    @Test
    fun `When get repositories from remote data source should return success`() = runBlockingTest {
        // Given
        whenever(dataSource.getRepositories(1)).thenReturn(mockResponse())

        // When
        val result = repository.getRepositories(1)

        // Then
        val data = result as GithubPresentation.SuccessResponse
        assertEquals(data.items[0].totalCount, 12)
    }

    @Test
    fun `When get repositories from remote data source should return failure`() = runBlockingTest {
        // Given
        whenever(dataSource.getRepositories(1)).thenReturn(null)

        // When
        val result = repository.getRepositories(1)

        // Then
        assertEquals(result, GithubPresentation.ErrorResponse)
    }

    @Test
    fun `When get repositories from remote data source should return empty list`() =
        runBlockingTest {
            // Given
            whenever(dataSource.getRepositories(1)).thenReturn(mockEmptyResponse())

            // When
            val result = repository.getRepositories(1)

            // Then
            assertEquals(result, GithubPresentation.EmptyResponse)
        }

    private fun mockResponse() =
        listOf(
            RepositoryDomain(
                totalCount = 12,
                id = 12,
                private = false,
                owner = OwnerDomain()

            )
        )

    private fun mockEmptyResponse() = emptyList<RepositoryDomain>()
}