package com.example.ktro

import org.koin.dsl.module

val appModule = module {
    single { ApiClient }
    single { PostRepository(get()) }
    factory { PostViewModel(get()) }
}
