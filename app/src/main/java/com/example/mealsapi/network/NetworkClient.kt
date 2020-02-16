package com.example.mealsapi.network

import com.example.mealsapi.misc.BASE_URL
import com.example.mealsapi.presenter.PresenterContract
import com.example.mealsapi.view_categories.model.CategoriesModel
import com.example.mealsapi.view_meal.MealsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient(private val presenter: PresenterContract.Network) : NetworkClientInterface {

    private val compositeDisposable = CompositeDisposable()

    override fun <T> retrieveData(model: T, category: String) {

        when (model) {
            is CategoriesModel -> {

                val observable = getRetrofit().getCategoriesClient()

                compositeDisposable.add( //Observable.subscribe() returns a disposable

                    observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ categories -> presenter.callSuccess(categories) },
                            { error -> presenter.callFailure(error) })
                )

            }
            is MealsModel -> {

                val observable = getRetrofit().getMealsClient(category)

                compositeDisposable.add( //Observable.subscribe() returns a disposable

                    observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ meals -> presenter.callSuccess(meals) },
                            { error -> presenter.callFailure(error)})
                )

            }
        }
    }

    override fun onDestroyCalled() {

        compositeDisposable.clear()  //clear the disposable links

    }


    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            ).build()
        )
        .build()
        .create(Client::class.java)

}