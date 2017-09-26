package com.todo.liszt.todoliszt.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.todo.liszt.todoliszt.Constants;
import com.todo.liszt.todoliszt.R;
import com.todo.liszt.todoliszt.adapters.CategoryListAdapter;
import com.todo.liszt.todoliszt.adapters.FirebaseCategoryViewHolder;
import com.todo.liszt.todoliszt.models.Category;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.newCategoryButton) Button mNewCategoryButton;
    @Bind(R.id.categoryRecyclerView) RecyclerView mCategoryRecyclerView;

    private DatabaseReference mCategoryReference;
    private ValueEventListener mCategoryReferenceListener;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    public ArrayList<Category> mCategories = new ArrayList<>();
    //private CategoryListAdapter mAdapter = new CategoryListAdapter(MainActivityFragment.this, mCategories);


    public MainActivityFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        ButterKnife.bind(this, view);

        mCategoryReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CATEGORY);

        mNewCategoryButton.setOnClickListener(this);
        setUpFirebaseAdapter();

        return view;
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder>(Category.class, R.layout.category_list_grid_item, FirebaseCategoryViewHolder.class, mCategoryReference) {
            @Override
            protected void populateViewHolder(FirebaseCategoryViewHolder viewHolder, Category model, int position) {
                viewHolder.bindCategory(model);
            }
        };
        mCategoryRecyclerView.setHasFixedSize(true);
        mCategoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mCategoryRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onClick(View view) {
        if (view == mNewCategoryButton) {
            Intent intent = new Intent(getActivity(), AddCategoryActivity.class);
            startActivity(intent);
        }
    }



}
