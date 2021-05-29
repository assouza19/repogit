package com.br.repogit.data.datasource

import com.br.repogit.data.api.GitHubService
import com.br.repogit.data.model.Owner
import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.data.model.Repository
import com.br.repogit.domain.model.RepositoryDomain
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val service: GitHubService = mock()
    private val dataSource: RemoteDataSource = RemoteDataSourceImpl(service)

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `list repositories should return the list from remote data source`() = runBlockingTest {
        // Given
        whenever(service.getTopRepositories(currentPage = 1)).thenReturn(mockResponse())

        // When
        val result = dataSource.getRepositories(1)

        // Then
        assertEquals(result, expectedResponse())
        assertEquals(result?.get(0)?.totalCount, 12)
    }

    private fun mockResponse() =
        Response.success(
            RepositoriesResponse(
                totalCount = 12,
                incompleteResults = false,
                items = listOf(
                    Repository(
                        id = 12,
                        private = false,
                        owner = Owner()
                    )
                )
            )
        )

    private fun expectedResponse() =
        listOf(
            RepositoryDomain(
                id = 12,
                name = null,
                fullName = null,
                totalCount = 12
            )
        )
}