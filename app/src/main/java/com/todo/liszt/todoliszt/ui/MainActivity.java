//package com.todo.liszt.todoliszt.ui;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.GridView;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.todo.liszt.todoliszt.Constants;
//import com.todo.liszt.todoliszt.R;
//import com.todo.liszt.todoliszt.adapters.CategoryListAdapter;
//import com.todo.liszt.todoliszt.adapters.FirebaseCategoryGridAdapter;
//import com.todo.liszt.todoliszt.adapters.FirebaseCategoryViewHolder;
//import com.todo.liszt.todoliszt.models.Category;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//
//    @Bind(R.id.newCategoryButton) Button mNewCategoryButton;
//    @Bind(R.id.categoryRecyclerView) RecyclerView mCategoryRecyclerView;
//
//    private DatabaseReference mCategoryReference;
//    private ValueEventListener mCategoryReferenceListener;
//    private FirebaseRecyclerAdapter mFirebaseAdapter;
//
//    public ArrayList<Category> mCategories = new ArrayList<>();
//    private CategoryListAdapter mAdapter = new CategoryListAdapter(MainActivity.this, mCategories);
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//        mCategoryReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_CATEGORY);
//        setUpFirebaseAdapter();
//
//        mNewCategoryButton = (Button) findViewById(R.id.newCategoryButton);
//        mNewCategoryButton.setOnClickListener(this);
//
//    }
//
//    private void setUpFirebaseAdapter() {
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder>(Category.class, R.layout.category_list_grid_item,
//                FirebaseCategoryViewHolder.class, mCategoryReference) {
//            @Override
//            protected void populateViewHolder(FirebaseCategoryViewHolder viewHolder, Category model, int position) {
//                viewHolder.bindCategory(model);
//            }
//        };
//        mCategoryRecyclerView = (RecyclerView) findViewById(R.id.categoryRecyclerView);
//        mCategoryRecyclerView.setHasFixedSize(false);
//        mCategoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, true));
//        mCategoryRecyclerView.setAdapter(mFirebaseAdapter);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        if (view == mNewCategoryButton) {
//            Intent intent = new Intent(MainActivity.this, AddCategoryActivity.class);
//            startActivity(intent);
//        }
//    }
//}