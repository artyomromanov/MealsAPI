package com.example.mealsapi.view_categories

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsapi.misc.ErrorContainer
import com.example.mealsapi.R
import com.example.mealsapi.ViewInterface
import com.example.mealsapi.presenter.Presenter
import com.example.mealsapi.view_categories.model.CategoriesModel
import com.example.mealsapi.view_categories.model.Category
import com.example.mealsapi.view_meal.DetailsActivity
import kotlinx.android.synthetic.main.activity_categories.*

class CategoriesActivity : AppCompatActivity(), ViewInterface, RecyclerViewClickListener {

    private val presenter = Presenter(this)

    lateinit var container : ErrorContainer

    val CATEGORY_KEY = "category"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        container = ErrorContainer(
            rv_categories,
            error_container_d,
            tv_error,
            pb_progress,
            btn_retry
        )

        //Upon loading the activity for the first time

        container.errorContainerStates(1)

        rv_categories.layoutManager = LinearLayoutManager(this)

        //Send the request to the Presenter to make the API Call(Retry if previously unsuccessful)
        presenter.getData(CategoriesModel(), CATEGORY_KEY)

        btn_retry.setOnClickListener{

            presenter.getData(CategoriesModel(), CATEGORY_KEY)

            container.errorContainerStates(4)

        }

    }

    override fun <T> displayData(list: T) {
        if (list is CategoriesModel) {
            val catAdapter = CategoriesAdapter(list.categories, this)
            rv_categories.adapter = catAdapter
            container.errorContainerStates(2)
        } else {
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

    override fun onRecyclerItemClicked(category: Category) {

        val intent = Intent(this@CategoriesActivity, DetailsActivity::class.java)
        intent.putExtra(CATEGORY_KEY, category.strCategory)
        startActivity(intent)

    }

    override fun onDestroy() {
        presenter.onDestroyCalled()
        super.onDestroy()
    }
}