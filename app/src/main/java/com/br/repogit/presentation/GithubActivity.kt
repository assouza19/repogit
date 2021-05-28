package com.br.repogit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.repogit.R
import com.br.repogit.databinding.ActivityGithubBinding
import com.br.repogit.presentation.adapter.GithubAdapter
import com.br.repogit.utils.orFalse
import com.br.repogit.utils.subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val VISIBLE_IMAGES_THRESHOLD = 10

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

        configureRecyclerView()
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

    private fun setupInfiniteScrolling(layoutManager: LinearLayoutManager) {
        viewBinding.recyclerViewRepositories.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0 && viewModel.hasNext.value.orFalse()) {
                    val totalItemCount = layoutManager.itemCount
                    val lastItem = layoutManager.findFirstVisibleItemPosition()

                    if (totalItemCount <= lastItem + VISIBLE_IMAGES_THRESHOLD) {
                        viewModel.nextPage()
                    }
                }
            }
        })
    }

    private fun configureRecyclerView() {
        viewBinding.recyclerViewRepositories.adapter = adapter

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewBinding.recyclerViewRepositories.layoutManager = layoutManager

        setupInfiniteScrolling(layoutManager)

    }
}