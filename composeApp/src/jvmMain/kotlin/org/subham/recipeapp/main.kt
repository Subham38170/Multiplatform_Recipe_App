package org.subham.recipeapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.dsl.module
import org.subham.recipeapp.data.local.DatabaseFactory
import org.subham.recipeapp.di.initKoin

private val jvmModule = module {
    single { DatabaseFactory() }
}
fun main() = application {
    initKoin(
        additionalModule = listOf(jvmModule)
    ){  }
    Window(
        onCloseRequest = ::exitApplication,
        title = "RecipeApp",
    ) {
        App()
    }
}