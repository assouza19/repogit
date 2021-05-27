package com.br.repogit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repository (
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("private")
    val private: Boolean,

    @SerialName("owner")
    val owner: Owner,

    @SerialName("description")
    val description: String? = null,

    @SerialName("forks_count")
    val forksCount: Long,

    @SerialName("stargazers_count")
    val stargazersCount: Long
)