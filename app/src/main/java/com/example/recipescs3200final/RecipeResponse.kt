package com.example.recipescs3200final

import com.google.gson.annotations.SerializedName

class RecipeResponse {
    @SerializedName("record")
    lateinit var recipeList: List<RecipeItem>
}