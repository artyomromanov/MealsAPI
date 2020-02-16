package com.example.mealsapi.presenter

interface PresenterContract {

    interface View {

        fun <T> getData(model : T, category : String) {}

        fun onDestroyCalled()
    }

    interface Network {

        fun <T> callSuccess(list : T){}

        fun callFailure(t : Throwable)

    }

}