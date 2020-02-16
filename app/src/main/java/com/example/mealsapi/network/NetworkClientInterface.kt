package com.example.mealsapi.network

interface NetworkClientInterface {

    fun <T>retrieveData(model :  T, category : String)

    fun onDestroyCalled()

}