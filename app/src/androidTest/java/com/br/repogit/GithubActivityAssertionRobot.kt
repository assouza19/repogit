package com.br.repogit

import com.br.repogit.utils.BaseRobot

fun check(func: GithubActivityAssertionRobot.() -> Unit) =
    GithubActivityAssertionRobot().apply { func() }

open class GithubActivityAssertionRobot : BaseRobot() {

    fun titleIsVisible() {
        checkViewHasText(R.id.textViewTitle, "RepoGit")
    }
}


