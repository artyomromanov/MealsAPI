package com.example.mealsapi.view_categories.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoriesModel {
    @SerializedName("categories")
    @Expose
    val categories: List<Category> = emptyList()
}

class Category {
    @SerializedName("idCategory")
    @Expose
    val idCategory: String? = null
    @SerializedName("strCategory")
    @Expose
    val strCategory: String? = null
    @SerializedName("strCategoryThumb")
    @Expose
    val strCategoryThumb: String? = null
    @SerializedName("strCategoryDescription")
    @Expose
    val strCategoryDescription: String? = null
}

