package com.example.mealsapi.view_meal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MealsModel {

    @SerializedName("meals")
    @Expose
    var meals: List<Meal> = emptyList()
}

class Meal {
    @SerializedName("strMeal")
    @Expose
    var strMeal: String? = null
    @SerializedName("strMealThumb")
    @Expose
    var strMealThumb: String? = null
    @SerializedName("idMeal")
    @Expose
    var idMeal: String? = null
}