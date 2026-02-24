package org.subham.recipeapp

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.dsl.module
import org.subham.recipeapp.data.local.DatabaseFactory
import org.subham.recipeapp.di.initKoin


private val iosModule = module {
    single { DatabaseFactory() }
}
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin(
            additionalModule = listOf(iosModule)
        ){}
    }
){ App() }