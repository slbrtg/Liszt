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
import com.todo.liszt.todoliszt.models.Task
import org.parceler.Parcels
import java.lang.reflect.Array
import java.util.ArrayList

class FirebaseTaskViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener {
    private val mContext: Context = mView.context
    val categories = ArrayList<Task>()
    val testString: String = "test"
    val ref = FirebaseDatabase
            .getInstance()
            .getReference(Constants.FIREBASE_CHILD_TASK)!!

    init {
        mView.setOnClickListener(this)
    }

    fun bindTask(task: Task) {
        val taskListItem = mView.findViewById<TextView>(R.id.taskListItem)
        taskListItem.text = task.name
    }



    override fun onClick(view: View) {

        if (view === itemView) {

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.mapTo(categories) { it.getValue(Task::class.java) }
//                    val itemPosition : Int = layoutPosition
//                    val category : Task = categories[itemPosition]
//                    val intent = Intent(mContext, TaskDetailActivity::class.java)
//                    intent.putExtra("category", Parcels.wrap(category))
//                    mContext.startActivity(intent)
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })

        }
    }

}