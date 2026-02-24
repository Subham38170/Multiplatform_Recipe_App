package org.subham.recipeapp.data.repo

import org.subham.recipeapp.domain.datasource.LocalDataSource
import org.subham.recipeapp.domain.datasource.RemoteDataSource
import org.subham.recipeapp.domain.model.RecipeItem
import org.subham.recipeapp.domain.repo.RecipeRepo

class RecipeRepoImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RecipeRepo {
    override suspend fun getRecipeList(): Result<List<RecipeItem>> {
        return try {
            val recipeList = localDataSource.getRecipeList()
            val count = recipeList.count()
            if (count > 0) Result.success(recipeList)
            else {
                val recipeListApiResponse = remoteDataSource.getRecipeList()
                localDataSource.saveAllRecipes(recipeListApiResponse)
                Result.success(recipeListApiResponse)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}