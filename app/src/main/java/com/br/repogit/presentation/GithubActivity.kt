package com.br.repogit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.repogit.R
import com.br.repogit.databinding.ActivityGithubBinding
import com.br.repogit.utils.subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubActivity : AppCompatActivity(R.layout.activity_github) {

    private val viewModel: GithubViewModel by viewModel()
    private val viewBinding by lazy {
        ActivityGithubBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        subscribeEvents()

        viewModel.getRepositories()

        val a = viewBinding.texto
    }

    private fun subscribeEvents() {
        viewModel.emptyResponseEvent.subscribe(this) {
            // SHOW EMPTY STATE
            hideLoading()
        }

        viewModel.errorResponseEvent.subscribe(this) {
            // SHOW ERROR STATE
            hideLoading()
        }

        viewModel.successResponseEvent.subscribe(this) {

            hideLoading()
        }
    }

    private fun hideLoading() {

    }
}