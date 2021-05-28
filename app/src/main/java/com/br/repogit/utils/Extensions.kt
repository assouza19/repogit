package com.br.repogit.utils

import com.br.repogit.data.model.RepositoriesResponse

fun RepositoriesResponse?.isNullOrEmpty(): Boolean {
    return this == null || this.items.isNullOrEmpty()
}

fun Boolean?.orFalse() = this ?: false

fun RepositoriesResponse?.orEmpty(): RepositoriesResponse =
    this ?: RepositoriesResponse(0, true, listOf())