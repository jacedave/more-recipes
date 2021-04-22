package com.example.recipescs3200final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), RecipeListFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, RecipeListFragment.newInstance()).commit()
        }

        // api url - https://api.jsonbin.io/b/606c86db639769186474a424/1
    }

    override fun onRecipeSelected(recipe: RecipeItem) {
        val fragment = RecipeFragment.newInstance(recipe)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
    }

}