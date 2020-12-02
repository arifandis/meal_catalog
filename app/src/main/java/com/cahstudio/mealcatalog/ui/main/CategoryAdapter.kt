package com.cahstudio.mealcatalog.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cahstudio.mealcatalog.R
import com.cahstudio.mealcatalog.datasource.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val context: Context, val catogoryList: List<Category>, val select: (Category) -> Unit):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun getItemCount(): Int = catogoryList.size


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val ivThum = view.ivThumb
        val tvName = view.tvCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = catogoryList[position]

        Picasso.get().load(category.strCategoryThumb).into(holder.ivThum)
        holder.tvName.text = category.strCategory

        holder.itemView.setOnClickListener {
            select(category)
        }
    }
}