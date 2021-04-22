package com.example.recipescs3200final

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class RecipeListViewModel : ViewModel() {

    val recipeListItemLiveData: LiveData<List<RecipeItem>>

    init {
        recipeListItemLiveData = Fetcher().fetchContents()
    }
}