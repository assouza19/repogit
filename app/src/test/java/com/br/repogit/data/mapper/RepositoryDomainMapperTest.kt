package com.br.repogit.data.mapper

import com.br.repogit.data.model.Owner
import com.br.repogit.data.model.RepositoriesResponse
import com.br.repogit.data.model.Repository
import com.br.repogit.domain.model.OwnerDomain
import com.br.repogit.domain.model.RepositoryDomain
import org.junit.Test
import kotlin.test.assertEquals

class RepositoryDomainMapperTest {

    private val mapper: RepositoryToDomainMapper = RepositoryToDomainMapper()

    @Test
    fun `when mapper should map to presentation`() {
        // When
        val result = mapper.map(mockResponse())

        // Then
        assertEquals(result, mockExpected())
    }

    private fun mockResponse() =
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

    private fun mockExpected() =
        listOf(
            RepositoryDomain(
                id = 12,
                private = false,
                owner = OwnerDomain("", ""),
                name = null,
                fullName = null,
                forksCount = 0,
                description = null,
                stargazersCount = 0,
                totalCount = 12
            )
        )

}