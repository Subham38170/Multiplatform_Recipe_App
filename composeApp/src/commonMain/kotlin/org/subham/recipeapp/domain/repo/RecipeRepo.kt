package org.subham.recipeapp.domain.repo

import org.subham.recipeapp.domain.model.RecipeItem

interface RecipeRepo {

    suspend fun getRecipeList(): Result<List<RecipeItem>>
}