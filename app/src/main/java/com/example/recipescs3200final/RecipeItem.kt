package com.example.recipescs3200final

data class RecipeItem(
    var id: Int,
    var name: String,
    var numofings: Int,
    var ingredients: String,
    var directions: String
    ) {
    override fun toString(): String {
        return name + ": --- Ingredients --- " + ingredients + " --- Directions --- " + directions
    }
}
