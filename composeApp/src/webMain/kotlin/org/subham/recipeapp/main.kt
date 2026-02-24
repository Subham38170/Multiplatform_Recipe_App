package org.subham.recipeapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.koin.dsl.module
import org.subham.recipeapp.data.local.DatabaseFactory
import org.subham.recipeapp.di.initKoin

private val jsModule = module {
    single { DatabaseFactory() }
}

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin(
        additionalModule = listOf(jsModule)
    ) { }
    ComposeViewport {
        App()
    }
}