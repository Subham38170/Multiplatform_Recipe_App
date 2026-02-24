package org.subham.recipeapp.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.subham.recipeapp.presentation.HomeScreen.HomeViewModel

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }

}