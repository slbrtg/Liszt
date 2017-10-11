package com.todo.liszt.todoliszt.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.todo.liszt.todoliszt.Constants
import com.todo.liszt.todoliszt.R
import com.todo.liszt.todoliszt.models.Cat

class AddCatActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mCatReference: DatabaseReference

    private lateinit var mButtonSubmitCategory: Button
    private lateinit var mInputCatName: EditText
    private lateinit var mInputCatDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        mCatReference = FirebaseDatabase
                .getInstance()
                .reference
                .child(Constants.FIREBASE_CHILD_CATEGORY)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cat)


        mButtonSubmitCategory = findViewById<Button>(R.id.addNewCatButton)
        mInputCatName = findViewById<EditText>(R.id.inputCatNameEditText)
        mInputCatDescription = findViewById<EditText>(R.id.inputCatDescriptionEditText)

        mButtonSubmitCategory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === mButtonSubmitCategory) {
            val pushId = mCatReference.push().key
            val name = mInputCatName.text.toString()
            val description = mInputCatDescription.text.toString()

            val newCat = Cat(name, description, pushId)
            saveCatToFirebase(newCat)
            val intent = Intent(this@AddCatActivity, MainActivity::class.java)
            startActivity(intent)

        }
    }

    fun saveCatToFirebase(newCat: Cat) {
        mCatReference.child(newCat.pushId).setValue(newCat)

        // Test Code for saving catKeys to user profiles
        // The following 2 lines create the map that will be added to the users node
        //var catKey = mutableMapOf<String, Any>()
        //catKey[newCat.pushId] = true


        // Pushes the map to a child called catKeys in the user's node
        // mCatReference.child(newCat.pushId).child("catKeys").updateChildren(catKey)
    }

}
