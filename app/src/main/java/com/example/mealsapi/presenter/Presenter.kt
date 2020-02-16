package com.example.mealsapi.presenter

import com.example.mealsapi.ViewInterface
import com.example.mealsapi.network.NetworkClient
import com.example.mealsapi.view_categories.model.CategoriesModel

class Presenter(private var viewInterface : ViewInterface?) : PresenterContract.View,
    PresenterContract.Network {

    private val networkClient = NetworkClient(this)

    override fun <T> getData(list : T, category : String) {

        networkClient.retrieveData(list, category)

    }

    override fun <T> callSuccess(list : T) {

        viewInterface?.displayData(list)

    }

    override fun callFailure(t: Throwable) {
        viewInterface?.showError(t)
    }

    override fun onDestroyCalled() {

        viewInterface = null //erase the link to the activity

        networkClient.onDestroyCalled() //pass the information to the network client that activity is being destroyed

    }

}

