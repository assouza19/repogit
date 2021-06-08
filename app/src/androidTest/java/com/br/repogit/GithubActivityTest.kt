package com.br.repogit

import org.junit.Test

class GithubActivityTest {

    @Test
    fun checkTitleIsVisible() {
        onActivity {
            setUp()
            launch()
        }
        check {
            titleIsVisible()
        }
    }
}