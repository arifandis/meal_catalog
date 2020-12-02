package com.cahstudio.mealcatalog.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahstudio.mealcatalog.R
import com.cahstudio.mealcatalog.datasource.model.CategoriesResponse
import com.cahstudio.mealcatalog.datasource.model.Category
import com.cahstudio.mealcatalog.datasource.network.ApiHelper
import com.cahstudio.mealcatalog.datasource.network.IApiService
import com.cahstudio.mealcatalog.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: CategoryAdapter
    private lateinit var mApi: IApiService
    private var mCategories = mutableListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        getCategories()
    }

    fun initialize(){
        mApi = ApiHelper.getClient().create(IApiService::class.java)

        val layoutManager = LinearLayoutManager(this)
        mAdapter =
            CategoryAdapter(
                this,
                mCategories,
                {category -> toDetail(category) })
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mAdapter
    }

    fun getCategories(){
        mApi.getCategories().enqueue(object : Callback<CategoriesResponse>{
            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<CategoriesResponse>,
                response: Response<CategoriesResponse>
            ) {
                if (response.isSuccessful){
                    mCategories.clear()
                    mCategories.addAll(response.body()!!.categories)
                    mAdapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(applicationContext, "Internal server error", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    fun toDetail(category: Category){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}