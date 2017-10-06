package com.todo.liszt.todoliszt.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.todo.liszt.todoliszt.Constants
import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.models.Category
import com.todo.liszt.todoliszt.ui.CategoryDetailActivity

import org.parceler.Parcels

import java.lang.reflect.Array
import java.util.ArrayList

class FirebaseCategoryViewHolder(private var mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener {
    private var mContext: Context = mView.context
    val categories = ArrayList<Category>()
    val ref = FirebaseDatabase
            .getInstance()
            .getReference(Constants.FIREBASE_CHILD_CATEGORY)!!

    init {
        mView.setOnClickListener(this)
    }


    fun bindCategory(category: Category) {
        val categoryGridItem = mView.findViewById<TextView>(R.id.categoryGridItem)
        categoryGridItem.text = category.name
    }

    override fun onClick(view: View) {

        if (view === itemView) {

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.mapTo(categories) { it.getValue(Category::class.java) }
                    val itemPosition : Int = layoutPosition
                    val category : Category = categories[itemPosition]
                    val intent = Intent(mContext, CategoryDetailActivity::class.java)
                    intent.putExtra("category", Parcels.wrap(category))
                    mContext.startActivity(intent)
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })

        }
    }

}
