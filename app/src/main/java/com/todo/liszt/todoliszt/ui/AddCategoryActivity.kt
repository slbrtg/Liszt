package com.todo.liszt.todoliszt.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.todo.liszt.todoliszt.Constants
import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.models.Category

import butterknife.Bind
import butterknife.ButterKnife

class AddCategoryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mCategoryReference: DatabaseReference

    internal lateinit var mButtonSubmitCategory: Button
    internal lateinit var mInputCategoryName: EditText
    internal lateinit var mInputCategoryDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        mCategoryReference = FirebaseDatabase
                .getInstance()
                .reference
                .child(Constants.FIREBASE_CHILD_CATEGORY)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        mButtonSubmitCategory = findViewById(R.id.addNewCatButton) as Button
        mInputCategoryName = findViewById(R.id.inputCatNameEditText) as EditText
        mInputCategoryDescription = findViewById(R.id.inputCatDescriptionEditText) as EditText

        mButtonSubmitCategory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === mButtonSubmitCategory) {
            val name = mInputCategoryName.text.toString()
            val description = mInputCategoryDescription.text.toString()

            val newCategory = Category(name, description)
            saveLocationToFirebase(newCategory)
            val intent = Intent(this@AddCategoryActivity, MainActivity::class.java)
            startActivity(intent)

        }
    }

    fun saveLocationToFirebase(newCategory: Category) {
        mCategoryReference.push().setValue(newCategory)
    }

}