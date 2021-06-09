package com.br.repogit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.repogit.R
import com.br.repogit.databinding.ActivityGithubBinding
import com.br.repogit.presentation.adapter.GithubAdapter
import com.br.repogit.presentation.adapter.RepositoryAdapterCallback
import com.br.repogit.utils.subscribe
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

internal const val VISIBLE_IMAGES_THRESHOLD = 15

class GithubActivity : AppCompatActivity(R.layout.activity_github) {

    private val viewModel: GithubViewModel by viewModel()
    private val viewBinding by lazy {
        ActivityGithubBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        GithubAdapter(::onRepositoryAdapterCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        subscribeEvents()

        configureRecyclerView()
        viewModel.getRepositories()
    }

    private fun subscribeEvents() {
        viewModel.loadingEvent.subscribe(this) {
            viewBinding.loading.isVisible = true
        }

        viewModel.scrollLoadingEvent.subscribe(this) {
            viewBinding.loadingNextPage.isVisible = true
        }

        viewModel.emptyResponseEvent.subscribe(this) {
            showFullResultsSnackbar()
            hideLoading()
        }

        viewModel.fullResultResponseEvent.subscribe(this) {
            showFullResultsSnackbar()
        }

        viewModel.errorResponseEvent.subscribe(this) {
            showGenericErrorSnackbar()
            hideLoading()
        }

        viewModel.successResponseEvent.subscribe(this) {
            viewBinding.appTitle.isVisible = true
            viewBinding.background.isVisible = true
            adapter.update(this.data)
            hideLoading()
        }

        viewModel.showToast.subscribe(this) {
            showErrorPaginationSnackbar()
        }
    }

    private fun hideLoading() {
        viewBinding.loading.isVisible = false
        viewBinding.loadingNextPage.isVisible = false
    }

    private fun configureRecyclerView() {
        viewBinding.recyclerViewRepositories.adapter = adapter

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewBinding.recyclerViewRepositories.layoutManager = layoutManager
    }

    private fun onRepositoryAdapterCallback(repositoryAdapterCallback: RepositoryAdapterCallback) =
        repositoryAdapterCallback.run {
            when (this) {
                RepositoryAdapterCallback.OnNextPage -> viewModel.nextPage()
            }
        }

    private fun showFullResultsSnackbar() {
        var snackbar: Snackbar? = null
        snackbar = Snackbar.make(
            viewBinding.recyclerViewRepositories,
            R.string.full_results,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.back_to_top) {
                viewBinding.recyclerViewRepositories.scrollToPosition(0)
                snackbar?.dismiss()
            }
        snackbar.show()
    }

    private fun showGenericErrorSnackbar() {
        var snackbar: Snackbar? = null
        snackbar = Snackbar.make(
            viewBinding.recyclerViewRepositories,
            R.string.couldnt_load,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.try_again) {
                viewModel.getRepositories()
                snackbar?.dismiss()
            }
        snackbar.show()
    }

    private fun showErrorPaginationSnackbar() {
        var snackbar: Snackbar? = null
        snackbar = Snackbar.make(
            viewBinding.recyclerViewRepositories,
            R.string.couldnt_load_next_page,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.try_again) {
                viewModel.nextPage(samePage = true)
                snackbar?.dismiss()
            }
        snackbar.show()
    }
}