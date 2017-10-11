package com.todo.liszt.todoliszt.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.models.Cat

import org.parceler.Parcels

import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.todo.liszt.todoliszt.Constants
import com.todo.liszt.todoliszt.adapters.FirebaseTaskViewHolder
import com.todo.liszt.todoliszt.models.Task

class CatDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mCat: Cat
    private lateinit var mCatName: TextView
    private lateinit var mCatDescription: TextView
    private lateinit var mAddNewTaskButton: Button
    private lateinit var mTaskRecyclerView: RecyclerView
    private var mTaskReference: DatabaseReference? = null
    private var mFirebaseAdapter: FirebaseRecyclerAdapter<*, *>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_detail)

        mCat = Parcels.unwrap<Cat>(intent.getParcelableExtra<Parcelable>("cat"))

        mCatName = findViewById(R.id.catName)
        mCatDescription = findViewById(R.id.catDescription)
        mAddNewTaskButton = findViewById(R.id.addNewTaskButton)


        mCatName.text = mCat.name
        mCatDescription.text = mCat.description
        mAddNewTaskButton.setOnClickListener(this)

        mTaskReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TASK)
                .child(mCat.pushId)
        setUpFirebaseAdapter()
    }

    override fun onClick(v: View) {
        if (v == mAddNewTaskButton) {
            val intent = Intent(this@CatDetailActivity, AddTaskActivity::class.java)
            intent.putExtra("cat", Parcels.wrap(mCat))
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
        mTaskRecyclerView.layoutManager = LinearLayoutManager(this@CatDetailActivity)
        mTaskRecyclerView.adapter = mFirebaseAdapter
    }
}
