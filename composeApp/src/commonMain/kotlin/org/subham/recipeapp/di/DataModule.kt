package org.subham.recipeapp.di

import org.koin.dsl.module
import org.subham.recipeapp.data.local.LocalDataSourceImpl
import org.subham.recipeapp.data.remote.RemoteDataSourceImpl
import org.subham.recipeapp.data.repo.RecipeRepoImpl
import org.subham.recipeapp.domain.datasource.LocalDataSource
import org.subham.recipeapp.domain.datasource.RemoteDataSource
import org.subham.recipeapp.domain.repo.RecipeRepo

val dataModule = module {

    single<LocalDataSource> { LocalDataSourceImpl(get()) }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    single<RecipeRepo> { RecipeRepoImpl(get(), get()) }
}