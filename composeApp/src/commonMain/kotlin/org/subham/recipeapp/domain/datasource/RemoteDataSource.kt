package org.subham.recipeapp.domain.datasource

import org.subham.recipeapp.domain.model.RecipeItem

interface RemoteDataSource {

    suspend fun getRecipeList(): List<RecipeItem>
}