package com.example.recipescs3200final

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.Resources
import android.graphics.LightingColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
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
    private lateinit var darkModeButton: Button
    private lateinit var background: LinearLayout
    private lateinit var darkbutton: View


    private var darkMode: Boolean = false

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
        darkModeButton = view.findViewById(R.id.dark_button)
        background = view.findViewById(R.id.background)
        darkbutton = view.findViewById(R.id.dark_button)

        return view
    }

    override fun onStart() {
        super.onStart()

        shareButton.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getRecipeReport())
                putExtra(Intent.EXTRA_SUBJECT, recipe.name)
            }.also { intent ->
                startActivity(intent)
            }
        }

        darkModeButton.setOnClickListener {
            if (darkMode) {
                this.activity?.setTheme(R.style.AppTheme)
                darkMode = false
                startAnimation()
            } else {
                this.activity?.setTheme(R.style.AppThemeDark)
                darkMode = true
                startAnimation()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextView.text = recipe.name
        ingredientsTextView.text = recipe.ingredients
        directionsTextView.text = recipe.directions
    }

    private fun getRecipeReport(): String {
        return recipe.toString()
    }

    private fun startAnimation() {

        val lightColor: Int? = this.context?.let { it1 -> ContextCompat.getColor(it1, R.color.colorPrimary) }
        val darkColor: Int? = this.context?.let { it1 -> ContextCompat.getColor(it1, R.color.colorPrimaryDark) }

        val buttonYStart = darkModeButton.top.toFloat()
        val buttonYEnd = darkModeButton.bottom.toFloat() + 30

        var backgroundAnimator: ObjectAnimator? = null

        if (darkMode) {
            if (lightColor != null) {
                if (darkColor != null) {
                    backgroundAnimator = ObjectAnimator.ofInt(
                        background, "backgroundColor", darkColor, lightColor)
                        .setDuration(3000)
                    backgroundAnimator.setEvaluator(ArgbEvaluator())
                }
            }
        }
        else {
                if (lightColor != null) {
                    if (darkColor != null) {
                        backgroundAnimator = ObjectAnimator.ofInt(
                            background, "backgroundColor", lightColor, darkColor
                            ).setDuration(3000)
                        backgroundAnimator.setEvaluator(ArgbEvaluator())
                    }
                }
            // I need help with animating this. It isn't working
        }

        val heightAnimator = ObjectAnimator.ofFloat(darkbutton,
            "y", buttonYStart, buttonYEnd)
            .setDuration(3000)

        val heightAnimator2 = ObjectAnimator.ofFloat(darkbutton,
            "y", buttonYEnd, buttonYStart)
            .setDuration(3000)

        if (darkMode) {
            heightAnimator.start()
            backgroundAnimator?.start()
        } else {
            heightAnimator2.start()
            backgroundAnimator?.start()
        }


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