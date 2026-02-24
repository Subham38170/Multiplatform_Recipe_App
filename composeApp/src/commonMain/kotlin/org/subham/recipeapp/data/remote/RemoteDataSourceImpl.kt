package org.subham.recipeapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.subham.recipeapp.data.remote.dto.RecipeDto
import org.subham.recipeapp.data.remote.mappers.toRecipeItem
import org.subham.recipeapp.domain.datasource.RemoteDataSource
import org.subham.recipeapp.domain.model.RecipeItem

class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun getRecipeList(): List<RecipeItem> {
        val httpResponse = httpClient.get("${BASE_URL}search.php?f=a")
        val recipeListApiResponse = httpResponse.body<RecipeDto>()

        return recipeListApiResponse.meals?.mapNotNull { it?.toRecipeItem() } ?: emptyList()
    }

    companion object {
        const val BASE_URL = "http://www.themealdb.com/api/json/v1/1/"
    }
}