package org.subham.recipeapp.data.local.dao

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOne
import org.subham.recipeapp.data.local.DbHelper
import org.subham.recipeapp.data.local.mappers.toRecipeItem
import org.subham.recipeapp.domain.model.RecipeItem


interface RecipeDao {
    suspend fun clearAllRecipes()
    suspend fun insertRecipe(
        recipe: RecipeItem
    )

    suspend fun deleteRecipeById(
        id: Long
    )

    suspend fun updateRecipe(
        recipe: RecipeItem
    )

    suspend fun getAllRecipeItems(): List<RecipeItem>

    suspend fun getRecipeById(id: Long): RecipeItem

    suspend fun saveAllRecipes(recipeList: List<RecipeItem>)
}

class RecipeDaoImpl(
    private val dbHelper: DbHelper
) : RecipeDao {
    override suspend fun clearAllRecipes() {

        dbHelper.withDatabse { db ->

            db.recipeEntityQueries.deleteAllRecipe()
        }
    }

    override suspend fun insertRecipe(
        recipe: RecipeItem
    ) {
        dbHelper.withDatabse { db ->
            db.recipeEntityQueries.insertRecipe(
                id = recipe.id,
                title = recipe.title,
                description = recipe.description,
                instructions = recipe.instructions,
                imageUrl = recipe.imageUrl,
                youtubeLink = recipe.youtubeLink,
                rating = recipe.rating,
                ingredients = recipe.ingredients,
                isFavorite = if (recipe.isFavorite) 1 else 0,
                area = recipe.area,
                category = recipe.category,
                difficulty = recipe.difficulty,
                duration = recipe.duration
            )
        }
    }

    override suspend fun deleteRecipeById(id: Long) {
        dbHelper.withDatabse { db ->
            db.recipeEntityQueries.deleteRecipeById(id)
        }
    }

    override suspend fun updateRecipe(recipe: RecipeItem) {
        dbHelper.withDatabse { db ->
            db.recipeEntityQueries.updateRecipe(
                title = recipe.title,
                description = recipe.description,
                duration = recipe.duration,
                difficulty = recipe.difficulty,
                id = recipe.id,
                area = recipe.area,
                youtubeLink = recipe.youtubeLink,
                isFavorite = if (recipe.isFavorite) 1 else 0,
                ingredients = recipe.ingredients,
                category = recipe.category,
                instructions = recipe.instructions,
                rating = recipe.rating
            )
        }
    }

    override suspend fun getAllRecipeItems(): List<RecipeItem> {
        return dbHelper.withDatabse { db ->
            db.recipeEntityQueries.selectAllRecipes().awaitAsList().map {
                    it.toRecipeItem()
                }
        }
    }

    override suspend fun getRecipeById(id: Long): RecipeItem {
        return dbHelper.withDatabse { db ->
            db.recipeEntityQueries.selectRecipeById(id).awaitAsOne().toRecipeItem()
        }
    }

    override suspend fun saveAllRecipes(recipeList: List<RecipeItem>) {
        dbHelper.withDatabse { db ->
            recipeList.forEach { recipe ->

                db.recipeEntityQueries.updateRecipe(
                    title = recipe.title,
                    description = recipe.description,
                    duration = recipe.duration,
                    difficulty = recipe.difficulty,
                    id = recipe.id,
                    area = recipe.area,
                    youtubeLink = recipe.youtubeLink,
                    isFavorite = if (recipe.isFavorite) 1 else 0,
                    ingredients = recipe.ingredients,
                    category = recipe.category,
                    instructions = recipe.instructions,
                    rating = recipe.rating
                )
            }
        }
    }
}


