package com.br.repogit.utils

interface Mapper<S, T> {
    fun map(source: S): T
}