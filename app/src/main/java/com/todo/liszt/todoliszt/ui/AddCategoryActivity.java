package com.todo.liszt.todoliszt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.todo.liszt.todoliszt.Constants;
import com.todo.liszt.todoliszt.R;
import com.todo.liszt.todoliszt.models.Category;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mCategoryReference;

    @Bind(R.id.buttonSubmitCategory) Button mButtonSubmitCategory;
    @Bind(R.id.inputCategoryName) EditText mInputCategoryName;
    @Bind(R.id.inputCategoryDescription) EditText mInputCategoryDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mCategoryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_CATEGORY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        ButterKnife.bind(this);

        mButtonSubmitCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonSubmitCategory) {
            String name = mInputCategoryName.getText().toString();
            String description = mInputCategoryDescription.getText().toString();

            Category newCategory = new Category(name, description);
            saveLocationToFirebase(newCategory);
            Intent intent = new Intent(AddCategoryActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

    public void saveLocationToFirebase(Category newCategory) {
        mCategoryReference.push().setValue(newCategory);
    }

}
