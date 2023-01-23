package com.eslirodrigues.tutorials.di_koin.di

import com.eslirodrigues.tutorials.di_koin.data.api.KoinApi
import com.eslirodrigues.tutorials.di_koin.data.repository.KoinRepository
import com.eslirodrigues.tutorials.di_koin.ui.viewmodel.KoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    single { KoinApi(get()) }
//    singleOf(::KoinApi)

    single<KoinRepository> {
        KoinRepository(
            koinApi = get(),
            stringOne = "StringOne",
            stringTwo = "StringTwo"
        )
    }

    single(named("stringFour")) { "StringFour" }
    single(named("stringFive")) { "StringFive" }
}

val viewModelModule = module {
    viewModel { KoinViewModel(get()) }
//    viewModelOf(::KoinViewModel)
}