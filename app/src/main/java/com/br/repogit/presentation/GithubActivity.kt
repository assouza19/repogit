package com.br.repogit.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.br.repogit.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubActivity : AppCompatActivity(R.layout.activity_github) {

    private val viewModel: GithubViewModel by viewModel()
//    private val viewBinding = GithubActivityBinding()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        viewModel.getRepositories()
    }
}