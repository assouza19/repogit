package com.br.repogit.utils

import com.br.repogit.data.model.RepositoriesResponse

object Extensions{
    fun isNullOrEmpty(data: RepositoriesResponse?): Boolean {
        return data == null || data.items.isNullOrEmpty()
    }

    fun orEmpty(data: RepositoriesResponse?): RepositoriesResponse =
        data ?: RepositoriesResponse(0, true, listOf())

    fun ordinalOf(i: Int) = "$i" + if (i % 100 in 11..13) "th" else when (i % 10) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> "th"
    }
}