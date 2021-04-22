package com.example.recipescs3200final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_RECIPE_ID = "recipe_ID"
private const val ARG_RECIPE_NAME = "recipe_name"
private const val ARG_RECIPE_NUMOFING = "recipe_numofing"
private const val ARG_RECIPE_INGREDIENTS = "recipe_ingredients"
private const val ARG_RECIPE_DIRECTIONS = "recipe_directions"

class RecipeFragment : Fragment() {
    private lateinit var recipe: RecipeItem
    private lateinit var nameTextView: TextView
    private lateinit var ingredientsTextView: TextView
    private lateinit var directionsTextView: TextView
    private lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = RecipeItem(1, "jsad", 3, "sdfsd, dsfa, ds d", "KD kdskdsk")
        recipe.id = arguments?.getSerializable(ARG_RECIPE_ID) as Int
        recipe.name = arguments?.getSerializable(ARG_RECIPE_NAME) as String
        recipe.numofings = arguments?.getSerializable(ARG_RECIPE_NUMOFING) as Int
        recipe.ingredients = arguments?.getSerializable(ARG_RECIPE_INGREDIENTS) as String
        recipe.directions = arguments?.getSerializable(ARG_RECIPE_DIRECTIONS) as String
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        nameTextView = view.findViewById(R.id.name)
        ingredientsTextView = view.findViewById(R.id.ingredients)
        directionsTextView = view.findViewById(R.id.directions)
        shareButton = view.findViewById(R.id.share_button)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextView.text = recipe.name
        ingredientsTextView.text = recipe.ingredients
        directionsTextView.text = recipe.directions
    }

    companion object {
        fun newInstance(recipeItem: RecipeItem) : RecipeFragment {
            val fragment = RecipeFragment()

            val arguments = Bundle()
            arguments.putSerializable(ARG_RECIPE_ID, recipeItem.id)
            arguments.putSerializable(ARG_RECIPE_NAME, recipeItem.name)
            arguments.putSerializable(ARG_RECIPE_NUMOFING, recipeItem.numofings)
            arguments.putSerializable(ARG_RECIPE_INGREDIENTS, recipeItem.ingredients)
            arguments.putSerializable(ARG_RECIPE_DIRECTIONS, recipeItem.directions)
            fragment.arguments = arguments

            return fragment
        }
    }
}