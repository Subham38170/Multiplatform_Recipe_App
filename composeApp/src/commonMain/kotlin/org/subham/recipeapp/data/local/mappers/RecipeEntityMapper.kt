package org.subham.recipeapp.data.local.mappers

import org.subham.recipeapp.Recipe
import org.subham.recipeapp.domain.model.RecipeItem

fun Recipe.toRecipeItem(): RecipeItem {
    return RecipeItem(
        id = this.id,
        title = this.title,
        description = this.description,
        category = this.category,
        area = this.area,
        imageUrl = this.imageUrl,
        youtubeLink = this.youtubeLink,
        ingredients = this.ingredients,
        instructions = this.instructions,
        isFavorite = this.isFavorite == 1L,
        rating = this.rating,
        duration = this.duration ?: "20 Mins",
        difficulty = this.difficulty ?: "Easy"
    )
}