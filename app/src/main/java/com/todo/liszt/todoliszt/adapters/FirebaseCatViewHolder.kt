package com.todo.liszt.todoliszt.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.todo.liszt.todoliszt.Constants
import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.models.Cat
import com.todo.liszt.todoliszt.ui.CatDetailActivity

import org.parceler.Parcels

import java.util.ArrayList

class FirebaseCatViewHolder(private var mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener {
    private var mContext: Context = mView.context
    val categories = ArrayList<Cat>()
    val ref = FirebaseDatabase
            .getInstance()
            .getReference(Constants.FIREBASE_CHILD_CATEGORY)!!

    init {
        mView.setOnClickListener(this)
    }


    fun bindCategory(cat: Cat) {
        val categoryGridItem = mView.findViewById<TextView>(R.id.categoryGridItem)
        categoryGridItem.text = cat.name
    }

    override fun onClick(view: View) {

        if (view === itemView) {

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.mapTo(categories) { it.getValue(Cat::class.java) }
                    val itemPosition : Int = layoutPosition
                    val cat: Cat = categories[itemPosition]
                    val intent = Intent(mContext, CatDetailActivity::class.java)
                    intent.putExtra("cat", Parcels.wrap(cat))
                    mContext.startActivity(intent)
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })

        }
    }

}
