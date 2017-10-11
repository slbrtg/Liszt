package com.todo.liszt.todoliszt.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.todo.liszt.todoliszt.Constants
import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.models.Cat
import com.todo.liszt.todoliszt.models.Task
import org.parceler.Parcels


class AddTaskActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mTaskReference: DatabaseReference
    private lateinit var mCat: Cat
    private lateinit var mInputTaskNameEditText: EditText
    private lateinit var mInputTaskDescriptionEditText: EditText
    private lateinit var mAddNewTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        mTaskReference = FirebaseDatabase
                .getInstance()
                .reference
                .child(Constants.FIREBASE_CHILD_TASK)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        mCat = Parcels.unwrap<Cat>(intent.getParcelableExtra<Parcelable>("cat"))

        mInputTaskNameEditText = findViewById(R.id.inputTaskNameEditText)
        mInputTaskDescriptionEditText = findViewById(R.id.inputTaskDescriptionEditText)
        mAddNewTaskButton = findViewById(R.id.addNewTaskButton)

        mAddNewTaskButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v == mAddNewTaskButton) {
            val name = mInputTaskNameEditText.text.toString()
            val description = mInputTaskDescriptionEditText.text.toString()
            val newTask = Task(name, description, mCat.pushId)
            saveTaskToFirebase(newTask)

            val intent = Intent(this@AddTaskActivity, CatDetailActivity::class.java)
            intent.putExtra("cat", Parcels.wrap(mCat))
            startActivity(intent)
        }
    }

    fun saveTaskToFirebase(newTask: Task) {
        mTaskReference.child(mCat.pushId).push().setValue(newTask)
    }
}
