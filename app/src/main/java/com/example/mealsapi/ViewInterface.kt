package com.example.mealsapi

interface ViewInterface{

    fun <T> displayData(list : T)

    fun showError(t : Throwable)

}