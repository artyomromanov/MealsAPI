package com.example.mealsapi.view_categories

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsapi.misc.DESCRIPTION_LENGTH
import com.example.mealsapi.R
import com.example.mealsapi.view_categories.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_item.view.*


class CategoriesAdapter(val categoriesList: List<Category>, val listener: RecyclerViewClickListener) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return categoriesList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {

            //load and crop the images
            val fullMovieUrl = categoriesList[position].strCategoryThumb
            Picasso
                .get()
                .load(fullMovieUrl)
                .resize(250, 250)
                .centerCrop()
                .into(itemView.iv_thumb)

            //title
            itemView.tv_title.text = categoriesList[position].strCategory


            itemView.tv_description.text =
                formatString(categoriesList[position].strCategoryDescription!!)

            itemView.tv_description.movementMethod = ScrollingMovementMethod()

            itemView.setOnClickListener { listener.onRecyclerItemClicked(categoriesList[position]) }
        }

        // function to shorten the description.
        private fun formatString(input: String): String {

            var formattedString = input

            val dotIndex = input.indexOf(".", 0, false)

            if (dotIndex != -1) {

                formattedString = input.substring(0, dotIndex + 1)
            }

            if (formattedString.length > DESCRIPTION_LENGTH) formattedString = input.substring(
                0,
                DESCRIPTION_LENGTH
            ) + "..."

            return formattedString
        }

    }
}