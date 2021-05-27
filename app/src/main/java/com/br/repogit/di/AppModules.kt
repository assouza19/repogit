package com.br.repogit.di

import com.br.repogit.data.api.RetrofitClient
import com.br.repogit.data.datasource.RemoteDataSource
import com.br.repogit.data.datasource.RemoteDataSourceImpl
import com.br.repogit.data.mapper.RepositoryDomainMapper
import com.br.repogit.data.repository.GithubRepositoryImpl
import com.br.repogit.domain.repository.GithubRepository
import com.br.repogit.domain.usecase.GetRepositoriesUseCase
import com.br.repogit.presentation.mapper.RepositoryPresentationMapper
import org.koin.dsl.module

val domainModules = module {
    factory { GetRepositoriesUseCase(repository = get()) }
}

val dataModules = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(api = get()) }
    factory<GithubRepository> { GithubRepositoryImpl(remoteDataSource = get(), mapper = get()) }
    factory { RepositoryDomainMapper() }
}

val presentationModules = module {
    factory { RepositoryPresentationMapper() }
}

val anotherModules = module {
    single { RetrofitClient.newInstance() }
}