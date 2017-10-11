package com.todo.liszt.todoliszt.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button

import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.todo.liszt.todoliszt.Constants
import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.adapters.FirebaseCatViewHolder
import com.todo.liszt.todoliszt.models.Cat

import butterknife.ButterKnife

class MainActivity : AppCompatActivity(), View.OnClickListener {

    internal lateinit var mNewCategoryButton: Button
    internal lateinit var mCategoryRecyclerView: RecyclerView

    private var mCategoryReference: DatabaseReference? = null
    private var mFirebaseAdapter: FirebaseRecyclerAdapter<*, *>? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        mCategoryReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CATEGORY)
        setUpFirebaseAdapter()

        mNewCategoryButton = findViewById<Button>(R.id.newCategoryButton)
        mNewCategoryButton.setOnClickListener(this)

    }

    private fun setUpFirebaseAdapter() {
        mFirebaseAdapter = object : FirebaseRecyclerAdapter<Cat, FirebaseCatViewHolder>(Cat::class.java, R.layout
                .cat_list_grid_item,
                FirebaseCatViewHolder::class.java, mCategoryReference) {
            override fun populateViewHolder(viewHolder: FirebaseCatViewHolder, model: Cat, position: Int) {
                viewHolder.bindCategory(model)
            }
        }
        mCategoryRecyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)
        mCategoryRecyclerView.setHasFixedSize(true)
        mCategoryRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, true)
        mCategoryRecyclerView.adapter = mFirebaseAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mFirebaseAdapter!!.cleanup()
    }


    override fun onClick(view: View) {
        if (view === mNewCategoryButton) {
            val intent = Intent(this@MainActivity, AddCatActivity::class.java)
            startActivity(intent)
        }
    }
}