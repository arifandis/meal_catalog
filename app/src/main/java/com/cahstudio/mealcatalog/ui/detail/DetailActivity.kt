package com.cahstudio.mealcatalog.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahstudio.mealcatalog.R
import com.cahstudio.mealcatalog.datasource.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = "Detail"

        getData()
    }

    fun getData(){
        val category = intent.getParcelableExtra<Category>("category")

        Picasso.get().load(category?.strCategoryThumb).into(ivThumb)
        tvCategory.text = category?.strCategory
        tvDesc.text = category?.strCategoryDescription
    }
}