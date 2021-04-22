package com.example.recipescs3200final

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipescs3200final.api.MyAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "RecipeListFragment"

class RecipeListFragment : Fragment(){

    interface Callbacks {
        fun onRecipeSelected(recipeItem: RecipeItem)
    }

    private var callbacks: Callbacks? = null

    private lateinit var recipeListViewModel: RecipeListViewModel
    private lateinit var recipeRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        recipeRecyclerView = view.findViewById(R.id.recipe_recycler_view)
        recipeRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeListViewModel.recipeListItemLiveData.observe(viewLifecycleOwner, Observer { recipeItems ->
            recipeRecyclerView.adapter = RecipeAdapter(recipeItems)
        })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class RecipeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var recipe: RecipeItem

        init {
            itemView.setOnClickListener(this)
        }

        val nameTextView: TextView = itemView.findViewById(R.id.recipe_name)
        val ingredientsTextView: TextView = itemView.findViewById(R.id.recipe_ingredients)

        fun bind(recipe: RecipeItem) {
            this.recipe = recipe
            nameTextView.text = this.recipe.name
            ingredientsTextView.text = this.recipe.ingredients
        }

        override fun onClick(v: View?) {
            callbacks?.onRecipeSelected(recipe)
        }
    }

    private inner class RecipeAdapter( var recipes: List<RecipeItem> ) : RecyclerView.Adapter<RecipeHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_recipe, parent, false)
            return RecipeHolder(view)
        }

        override fun getItemCount() = recipes.size

        override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
            val recipe = recipes[position]
            holder.bind(recipe)
        }
    }

    companion object {
        fun newInstance() = RecipeListFragment()
    }
}