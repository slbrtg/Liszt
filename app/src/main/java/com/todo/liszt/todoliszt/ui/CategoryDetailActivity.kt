package com.todo.liszt.todoliszt.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.models.Category

import org.parceler.Parcels

import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.widget.Button

class CategoryDetailActivity : AppCompatActivity(), View.OnClickListener {
    internal lateinit var mCategory: Category
    internal lateinit var mCategoryName: TextView
    internal lateinit var mCategoryDescription: TextView
    internal lateinit var mAddNewTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)

        mCategory = Parcels.unwrap<Category>(intent.getParcelableExtra<Parcelable>("category"))

        mCategoryName = findViewById(R.id.catName)
        mCategoryDescription = findViewById(R.id.catDescription)
        mAddNewTaskButton = findViewById(R.id.addNewTaskButton)


        mCategoryName.text = mCategory.name
        mCategoryDescription.text = mCategory.description
        mAddNewTaskButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v == mAddNewTaskButton) {
            val intent = Intent(this@CategoryDetailActivity, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
}
