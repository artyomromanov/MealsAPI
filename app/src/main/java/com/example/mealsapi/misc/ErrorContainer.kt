package com.example.mealsapi.misc

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsapi.R

class ErrorContainer(
    private val recyclerView: RecyclerView,
    private val errorContainer: LinearLayout,
    private val errorText: TextView,
    private val pbProgress: ProgressBar,
    private val btnRetry: Button
) {
    fun errorContainerStates(type: Int) {

        when (type) {

            1 -> {  //first appearance
                recyclerView.visibility = View.GONE
                errorContainer.visibility = View.VISIBLE
                pbProgress.visibility = View.VISIBLE
                errorText.visibility = View.VISIBLE
                btnRetry.visibility = View.GONE
            }

            2 -> { //success
                recyclerView.visibility = View.VISIBLE
                errorContainer.visibility = View.GONE
            }

            3 -> { //error
                errorContainer.visibility = View.VISIBLE
                pbProgress.visibility = View.GONE
                errorText.visibility = View.VISIBLE
                btnRetry.visibility = View.VISIBLE
            }

            4 -> { //while loading
                errorContainer.visibility = View.VISIBLE
                pbProgress.visibility = View.VISIBLE
                errorText.setText(R.string.txt_loading)
                errorText.visibility = View.VISIBLE
                btnRetry.visibility = View.GONE
            }

        }
    }

}
