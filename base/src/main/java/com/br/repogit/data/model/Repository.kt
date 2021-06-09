package com.br.repogit.data.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("full_name")
    val fullName: String? = null,

    @SerializedName("private")
    val private: Boolean = false,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("forks_count")
    val forksCount: Int? = 0,

    @SerializedName("stargazers_count")
    val stargazersCount: Int? = 0
)