package com.todo.liszt.todoliszt.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.todo.liszt.todoliszt.R

import butterknife.Bind
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity(), View.OnClickListener {

    private val mCreateUserButton: Button by lazy { findViewById<Button>(R.id.createUserButton) }
    private val mNameEditText: EditText by lazy { findViewById<EditText>(R.id.nameEditText) }
    private val mEmailEditText: EditText by lazy { findViewById<EditText>(R.id.emailEditText) }
    private val mPasswordEditText: EditText by lazy { findViewById<EditText>(R.id.passwordEditText) }
    private val mConfirmPasswordEditText: EditText by lazy { findViewById<EditText>(R.id.confirmPasswordEditText) }
    private val mLoginTextView: TextView by lazy { findViewById<TextView>(R.id.loginTextView) }

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        createAuthStateListener()

        mLoginTextView.setOnClickListener(this)
        mCreateUserButton.setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener!!)
        }
    }

    override fun onClick(view: View) {

        if (view === mLoginTextView) {
            val intent = Intent(this@CreateAccountActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        if (view === mCreateUserButton) {
            createNewUser()
        }

    }

    private fun createNewUser() {
        val name = mNameEditText.text.toString().trim { it <= ' ' }
        val email = mEmailEditText.text.toString().trim { it <= ' ' }
        val password = mPasswordEditText.text.toString().trim { it <= ' ' }
        val confirmPassword = mConfirmPasswordEditText.text.toString().trim { it <= ' ' }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Authentication successful")
            } else {
                Toast.makeText(this@CreateAccountActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createAuthStateListener() {
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                val intent = Intent(this@CreateAccountActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    companion object {
        val TAG = CreateAccountActivity::class.java.simpleName!!
    }

}