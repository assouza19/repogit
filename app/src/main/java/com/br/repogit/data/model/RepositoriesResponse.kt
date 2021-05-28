package com.br.repogit.data.model

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
    @SerializedName("total_count")
    val totalCount: Long,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: List<Repository>
)