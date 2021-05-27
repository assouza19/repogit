package com.br.repogit.di

import com.br.repogit.data.api.RetrofitClient
import com.br.repogit.data.datasource.RemoteDataSource
import com.br.repogit.data.datasource.RemoteDataSourceImpl
import com.br.repogit.data.repository.GithubRepositoryImpl
import com.br.repogit.domain.repository.GithubRepository
import com.br.repogit.domain.usecase.GetRepositoriesUseCase
import com.br.repogit.presentation.GithubViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModules = module {
    factory { GetRepositoriesUseCase(repository = get()) }
}

val presentationModules = module {
    viewModel { GithubViewModel(getRepositoriesUseCase = get(), dispatcher = Dispatchers.IO) }
}

val dataModules = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(api = get()) }
    factory<GithubRepository> { GithubRepositoryImpl(remoteDataSource = get()) }
}

val anotherModules = module {
    single { RetrofitClient.newInstance() }
}