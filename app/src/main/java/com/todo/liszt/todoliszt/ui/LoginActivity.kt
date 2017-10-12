package com.todo.liszt.todoliszt.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.todo.liszt.todoliszt.R

import android.app.ProgressDialog
import android.content.Intent
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

import butterknife.Bind
import butterknife.ButterKnife

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val mPasswordLoginButton: Button by lazy { findViewById<Button>(R.id.passwordLoginButton) }
    private val mEmailEditText: EditText by lazy { findViewById<EditText>(R.id.emailEditText) }
    private val mPasswordEditText: EditText by lazy { findViewById<EditText>(R.id.passwordEditText) }
    private val mRegisterTextView: TextView by lazy { findViewById<TextView>(R.id.registerTextView) }

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var mAuthProgressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mRegisterTextView.setOnClickListener(this)
        mPasswordLoginButton.setOnClickListener(this)

        createAuthProgressDialog()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
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

    private fun loginWithPassword() {
        val email = mEmailEditText.text.toString().trim { it <= ' ' }
        val password = mPasswordEditText.text.toString().trim { it <= ' ' }

        if (email == "") {
            mEmailEditText.error = "Please enter your email"
            return
        }

        if (password == "") {
            mPasswordEditText.error = "Password cannot be blank"
            return
        }
        mAuthProgressDialog.show()
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful)
            mAuthProgressDialog.dismiss()

            if (!task.isSuccessful) {
                Log.w(TAG, "signInWithEmail", task.exception)
                Toast.makeText(this@LoginActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun createAuthProgressDialog() {
        mAuthProgressDialog = ProgressDialog(this)
        mAuthProgressDialog.setTitle("Loading...")
        mAuthProgressDialog.setMessage("Authenticating with Firebase...")
        mAuthProgressDialog.setCancelable(false)
    }

    override fun onClick(view: View) {

        if (view === mRegisterTextView) {
            val intent = Intent(this@LoginActivity, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (view === mPasswordLoginButton) {
            loginWithPassword()
        }

    }

    companion object {
        val TAG = LoginActivity::class.java.simpleName
    }
}
