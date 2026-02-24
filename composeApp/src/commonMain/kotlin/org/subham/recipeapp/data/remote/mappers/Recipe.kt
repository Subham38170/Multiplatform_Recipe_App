package org.subham.recipeapp.data.remote.mappers

import org.subham.recipeapp.data.remote.dto.Meal
import org.subham.recipeapp.domain.model.RecipeItem


fun Meal.toRecipeItem(): RecipeItem? {
    return this.idMeal?.let {
        RecipeItem(
            id = it.toLong(),
            title = this.strMeal ?: "",
            description = this.strMeal ?: "",
            category = this.strCategory ?: "",
            area = this.strArea ?: "",
            imageUrl = this.strMealThumb ?: "",
            youtubeLink = this.strYoutube,
            ingredients = listOfNotNull(
                "${this.strIngredient1}:${this.strMeasure1}",
                "${this.strIngredient2}:${this.strMeasure2}",
                "${this.strIngredient3}:${this.strMeasure3}",
                "${this.strIngredient4}:${this.strMeasure4}",
                "${this.strIngredient5}:${this.strMeasure5}",
                "${this.strIngredient6}:${this.strMeasure6}",
                "${this.strIngredient7}:${this.strMeasure7}",
                "${this.strIngredient8}:${this.strMeasure8}",
                "${this.strIngredient9}:${this.strMeasure9}",
                "${this.strIngredient10}:${this.strMeasure10}",
                "${this.strIngredient11}:${this.strMeasure11}",
                "${this.strIngredient12}:${this.strMeasure12}",
                "${this.strIngredient13}:${this.strMeasure13}",
                "${this.strIngredient14}:${this.strMeasure14}",
                "${this.strIngredient15}:${this.strMeasure15}",
            ),
            instructions = this.strInstructions?.split(".")?.map {
                it.trim().replace("\r\n", "").capitalizeFirstWord()
            }?.filter { it.isNotEmpty() } ?: emptyList(),
            isFavorite = false,
            rating = 3
        )
    }
}

fun String.capitalizeFirstWord() = this.replaceFirstChar { it.uppercase() }