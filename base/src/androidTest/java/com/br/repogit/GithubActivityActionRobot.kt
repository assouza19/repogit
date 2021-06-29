package com.br.repogit

import com.br.repogit.utils.BaseRobot

fun action(func: GithubActivityActionRobot.() -> Unit) =
    GithubActivityActionRobot().apply { func() }

open class GithubActivityActionRobot : BaseRobot() {

    fun clickOnConfirmButton() {

    }
}


