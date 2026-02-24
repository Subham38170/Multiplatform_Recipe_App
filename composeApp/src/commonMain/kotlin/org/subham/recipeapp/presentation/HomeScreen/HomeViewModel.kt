package org.subham.recipeapp.presentation.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.subham.recipeapp.domain.repo.RecipeRepo

class HomeViewModel(
    private val recipeRepo: RecipeRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {

        viewModelScope.launch {

        }
    }

    private fun getRecipeList() {
        viewModelScope.launch {
            val result = recipeRepo.getRecipeList()
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        recipeList = result.getOrDefault(emptyList()),
                        recipeListLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        recipeListLoading = false,
                        recipeListError = result.exceptionOrNull()?.message
                    )
                }
            }

        }
    }
}