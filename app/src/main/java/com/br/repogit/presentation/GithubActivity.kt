package com.br.repogit.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.br.repogit.R
import com.br.repogit.databinding.ActivityGithubBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubActivity : AppCompatActivity(R.layout.activity_github) {

    private val viewModel: GithubViewModel by viewModel()
    private val viewBinding by lazy {
        ActivityGithubBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel.getRepositories()

        val a = viewBinding.texto
    }
}