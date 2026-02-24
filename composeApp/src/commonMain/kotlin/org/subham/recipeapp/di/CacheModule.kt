package org.subham.recipeapp.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import org.subham.recipeapp.RecipeAppDb
import org.subham.recipeapp.data.local.DbHelper
import kotlin.coroutines.CoroutineContext

val cacheModule = module {

    single<CoroutineContext>{ Dispatchers.Default }
    single { CoroutineScope(get()) }


    single { DbHelper(get()) }
}