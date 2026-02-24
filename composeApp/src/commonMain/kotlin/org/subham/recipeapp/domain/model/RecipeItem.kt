package org.subham.recipeapp.domain.model

data class RecipeItem(
    val id: Long,
    val title: String,
    val description: String,
    val category: String,
    val area: String,
    val imageUrl: String? = null,
    val youtubeLink: String? = null,
    val ingredients: List<String>,
    val instructions: List<String>,
    val isFavorite: Boolean = false,
    val rating: Long = 0,
    val duration: String = "20 Mins",
    val difficulty: String = "Easy"
)