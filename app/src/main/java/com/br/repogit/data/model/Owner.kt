package com.br.repogit.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Owner (
    @SerialName("login")
    val name: String,

    @SerialName("avatar_url")
    val avatarURL: String
)