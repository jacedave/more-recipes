package com.example.recipescs3200final.api


import com.example.recipescs3200final.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyAPI {

    @GET("b/606c86db639769186474a424/1/")
    fun fetchContents(): Call<RecipeResponse>
}