package com.br.repogit.di

import com.br.repogit.data.api.GitHubService
import com.br.repogit.data.api.HttpClient
import com.br.repogit.data.api.RetrofitClient
import com.br.repogit.data.datasource.RemoteDataSource
import com.br.repogit.data.datasource.RemoteDataSourceImpl
import com.br.repogit.data.repository.GithubRepositoryImpl
import com.br.repogit.domain.repository.GithubRepository
import com.br.repogit.domain.usecase.GetRepositoriesUseCase
import com.br.repogit.presentation.GithubViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val BASE_URL = "https://api.github.com"

val domainModules = module {
    factory { GetRepositoriesUseCase(repository = get()) }
}

val presentationModules = module {
    viewModel { GithubViewModel(getRepositoriesUseCase = get(), dispatcher = Dispatchers.IO) }
}

val dataModules = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(service = get()) }
    factory<GithubRepository> { GithubRepositoryImpl(remoteDataSource = get()) }
}

val anotherModules = module {
    single { RetrofitClient(application = androidContext(), BASE_URL).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(GitHubService::class.java) }
}