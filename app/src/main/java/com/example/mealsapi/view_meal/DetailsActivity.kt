package com.example.mealsapi.view_meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsapi.R
import com.example.mealsapi.ViewInterface
import com.example.mealsapi.misc.ErrorContainer
import com.example.mealsapi.presenter.Presenter
import com.example.mealsapi.view_categories.model.CategoriesModel
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.btn_retry
import kotlinx.android.synthetic.main.activity_details.error_container_d
import kotlinx.android.synthetic.main.activity_details.pb_progress
import kotlinx.android.synthetic.main.activity_details.tv_error
import java.lang.RuntimeException

class DetailsActivity : AppCompatActivity(), ViewInterface {

    private val CATEGORY_KEY = "category"
    private val presenter = Presenter(this)

    lateinit var container : ErrorContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Upon loading the activity for the first time

        container = ErrorContainer(rv_meals, error_container_d, tv_error, pb_progress, btn_retry)

        container.errorContainerStates(1)

        rv_meals.layoutManager = LinearLayoutManager(this)

        val categoryClicked = intent.getStringExtra(CATEGORY_KEY)

        //Send the request to the Presenter to make the API Call(Retry if previously unsuccessful)

        presenter.getData(MealsModel(), categoryClicked!!)

        container.errorContainerStates(4)

        btn_retry.setOnClickListener{

            presenter.getData(MealsModel(), CATEGORY_KEY)

            container.errorContainerStates(4)

        }

    }

    @Throws(RuntimeException::class)
    override fun <T> displayData(list: T) {
        if (list is MealsModel) {
            val mealsAdapter = MealsAdapter(list.meals)
            rv_meals.adapter = mealsAdapter

            container.errorContainerStates(2)

        }else{
            throw RuntimeException(getString(R.string.txt_adapter_error))
        }
    }

    override fun showError(t: Throwable) {

        if (t.message!!.isEmpty()) {
            tv_error.setText(R.string.txt_error)
        } else {
            tv_error.text = t.message
        }

        container.errorContainerStates(3)

    }

    override fun onDestroy() {

        presenter.onDestroyCalled()
        super.onDestroy()
    }
}
