package com.br.repogit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.repogit.R
import com.br.repogit.databinding.ActivityGithubBinding
import com.br.repogit.presentation.adapter.GithubAdapter
import com.br.repogit.utils.subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubActivity : AppCompatActivity(R.layout.activity_github) {

    private val viewModel: GithubViewModel by viewModel()
    private val viewBinding by lazy {
        ActivityGithubBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        GithubAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        subscribeEvents()

        viewBinding.recyclerViewRepositories.adapter = adapter
        viewBinding.recyclerViewRepositories.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel.getRepositories()
    }

    private fun subscribeEvents() {
        viewModel.loadingEvent.subscribe(this) {
            viewBinding.loading.isVisible = true
        }

        viewModel.emptyResponseEvent.subscribe(this) {
            // SHOW EMPTY STATE
            hideLoading()
        }

        viewModel.errorResponseEvent.subscribe(this) {
            // SHOW ERROR STATE
            hideLoading()
        }

        viewModel.successResponseEvent.subscribe(this) {
            adapter.update(this.data)
            hideLoading()
        }
    }

    private fun hideLoading() {
        viewBinding.loading.isVisible = false
    }
}