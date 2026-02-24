package org.subham.recipeapp.domain.datasource

import org.subham.recipeapp.domain.model.RecipeItem

interface LocalDataSource {
    suspend fun getRecipeList(): List<RecipeItem>

    suspend fun saveAllRecipes(recipeList: List<RecipeItem>)
}