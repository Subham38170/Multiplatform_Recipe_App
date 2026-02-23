package org.subham.recipeapp

import androidx.compose.ui.window.ComposeUIViewController
import org.subham.recipeapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin{}
    }
){ App() }