package com.example.mealsapi.network

import com.example.mealsapi.misc.CATEGORIES_ENDPOINT
import com.example.mealsapi.misc.DETAILS_ENDPOINT
import com.example.mealsapi.view_categories.model.CategoriesModel
import com.example.mealsapi.view_meal.MealsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Client {

    @GET(CATEGORIES_ENDPOINT)
    fun getCategoriesClient() : Observable<CategoriesModel>

    @GET(DETAILS_ENDPOINT)
    fun getMealsClient(@Query("c") category : String) : Observable<MealsModel>

}