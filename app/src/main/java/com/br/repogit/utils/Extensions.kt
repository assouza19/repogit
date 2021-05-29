package com.br.repogit.utils

import com.br.repogit.data.model.RepositoriesResponse

fun RepositoriesResponse?.isNullOrEmpty(): Boolean {
    return this == null || this.items.isNullOrEmpty()
}

fun RepositoriesResponse?.orEmpty(): RepositoriesResponse =
    this ?: RepositoriesResponse(0, true, listOf())

fun ordinalOf(i: Int) = "$i" + if (i % 100 in 11..13) "th" else when (i % 10) {
    1 -> "st"
    2 -> "nd"
    3 -> "rd"
    else -> "th"
}