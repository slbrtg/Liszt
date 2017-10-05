package com.todo.liszt.todoliszt.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.models.Category

import org.parceler.Parcels

import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.todo.liszt.todoliszt.Constants
import com.todo.liszt.todoliszt.adapters.FirebaseTaskViewHolder
import com.todo.liszt.todoliszt.models.Task

class CategoryDetailActivity : AppCompatActivity(), View.OnClickListener {
    internal lateinit var mCategory: Category
    internal lateinit var mCategoryName: TextView
    internal lateinit var mCategoryDescription: TextView
    internal lateinit var mAddNewTaskButton: Button
    internal lateinit var mTaskRecyclerView: RecyclerView
    private var mTaskReference: DatabaseReference? = null
    private var mFirebaseAdapter: FirebaseRecyclerAdapter<*, *>? = null



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

        mTaskReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TASK)
                .child(mCategory.pushId)
        setUpFirebaseAdapter()
    }

    override fun onClick(v: View) {
        if (v == mAddNewTaskButton) {
            val intent = Intent(this@CategoryDetailActivity, AddTaskActivity::class.java)
            intent.putExtra("category", Parcels.wrap(mCategory))
            startActivity(intent)
        }
    }

    private fun setUpFirebaseAdapter() {
        mFirebaseAdapter = object : FirebaseRecyclerAdapter<Task, FirebaseTaskViewHolder>(Task::class.java, R.layout
                .task_list_item,
                FirebaseTaskViewHolder::class.java, mTaskReference) {
            override fun populateViewHolder(viewHolder: FirebaseTaskViewHolder, model: Task, position: Int) {
                viewHolder.bindTask(model)
            }
        }
        mTaskRecyclerView = findViewById<RecyclerView>(R.id.taskRecyclerView)
        mTaskRecyclerView.setHasFixedSize(true)
        mTaskRecyclerView.layoutManager = LinearLayoutManager(this@CategoryDetailActivity)
        mTaskRecyclerView.adapter = mFirebaseAdapter
    }
}
