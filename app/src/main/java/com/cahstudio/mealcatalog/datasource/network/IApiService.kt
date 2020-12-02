package com.cahstudio.mealcatalog.datasource.network

import com.cahstudio.mealcatalog.datasource.model.CategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface IApiService {

    @GET("categories.php")
    fun getCategories(): Call<CategoriesResponse>
}