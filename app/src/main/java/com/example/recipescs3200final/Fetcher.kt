package com.example.recipescs3200final

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipescs3200final.api.MyAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "Fetcher"

class Fetcher {

    private val myAPI: MyAPI

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        myAPI = retrofit.create(MyAPI::class.java)
    }

    fun fetchContents(): LiveData<List<RecipeItem>> {
        val responseLiveData: MutableLiveData<List<RecipeItem>> = MutableLiveData()

        val myRequest: Call<RecipeResponse> = myAPI.fetchContents()

        myRequest.enqueue(object: Callback<RecipeResponse> {
            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data", t)
            }

            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                Log.d(TAG, "Response received")
                val recipeResponse: RecipeResponse? = response.body()
                val recipeList: List<RecipeItem> = recipeResponse?.recipeList ?: mutableListOf()
                responseLiveData.value = recipeList
            }
        })
        return responseLiveData
    }
}