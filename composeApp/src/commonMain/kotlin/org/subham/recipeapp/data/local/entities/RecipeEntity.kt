package org.subham.recipeapp.data.local.entities

data class RecipeEntity(
    val id: Long,
    val title: String,
    val description: String,
    val category: String,
    val area: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val isFavorite: Boolean,
    val rating: Long,
    val duration: String = "20 Mins",
    val difficulty: String = "Easy"
)