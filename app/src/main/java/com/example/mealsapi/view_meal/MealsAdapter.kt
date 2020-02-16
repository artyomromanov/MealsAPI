package com.example.mealsapi.view_meal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsapi.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_item.view.*

class MealsAdapter(val mealsList: List<Meal>) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return mealsList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {

            //load and crop the images
            val fullMovieUrl = mealsList[position].strMealThumb
            Picasso
                .get()
                .load(fullMovieUrl)
                .resize(250, 250)
                .centerCrop()
                .into(itemView.iv_thumb)

            //title
            itemView.tv_title.text = mealsList[position].strMeal
        }
    }
}