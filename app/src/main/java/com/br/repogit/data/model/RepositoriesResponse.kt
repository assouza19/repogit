package com.br.repogit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoriesResponse (
    @SerialName("total_count")
    val totalCount: Long,

    @SerialName("incomplete_results")
    val incompleteResults: Boolean,

    @SerialName("items")
    val items: List<Repository>
)