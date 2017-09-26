package com.todo.liszt.todoliszt.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.todo.liszt.todoliszt.R;
import com.todo.liszt.todoliszt.models.Category;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;

import static android.R.attr.category;

public class CategoryDetailActivity extends AppCompatActivity {
    Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        mCategory = Parcels.unwrap(getIntent().getParcelableExtra("category"));


        CategoryDetailFragment fragment = new CategoryDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("category", Parcels.wrap(mCategory));
        fragment.setArguments(bundle);


    }
}
