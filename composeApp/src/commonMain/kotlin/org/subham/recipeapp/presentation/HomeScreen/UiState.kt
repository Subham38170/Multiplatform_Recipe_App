package org.subham.recipeapp.presentation.HomeScreen

import org.subham.recipeapp.domain.model.RecipeItem

data class UiState(
    val recipeList: List<RecipeItem> = emptyList(),

    val recipeListLoading: Boolean = true,
    val recipeListError: String? = null
)