package com.todo.liszt.todoliszt.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.todo.liszt.todoliszt.R


class AddTaskActivity : AppCompatActivity(), View.OnClickListener {
    internal lateinit var mInputTaskNameEditText: EditText
    internal lateinit var mInputTaskDescriptionEditText: EditText
    internal lateinit var mAddNewTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        mInputTaskNameEditText = findViewById(R.id.inputTaskNameEditText)
        mInputTaskDescriptionEditText = findViewById(R.id.inputTaskDescriptionEditText)
        mAddNewTaskButton = findViewById(R.id.addNewTaskButton)
    }

    override fun onClick(v: View?) {

    }
}
