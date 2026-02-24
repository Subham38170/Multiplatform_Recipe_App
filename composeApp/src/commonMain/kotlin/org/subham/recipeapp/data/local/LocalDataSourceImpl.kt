package org.subham.recipeapp.data.local

import org.subham.recipeapp.data.local.dao.RecipeDao
import org.subham.recipeapp.domain.datasource.LocalDataSource
import org.subham.recipeapp.domain.model.RecipeItem

class LocalDataSourceImpl(
    private val recipeDao: RecipeDao
) : LocalDataSource {
    override suspend fun getRecipeList(): List<RecipeItem> {

        return recipeDao.getAllRecipeItems()
    }

    override suspend fun saveAllRecipes(
        recipeList: List<RecipeItem>
    ) {
        recipeDao.saveAllRecipes(recipeList)
    }
}