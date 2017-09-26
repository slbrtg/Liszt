package com.todo.liszt.todoliszt.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.todo.liszt.todoliszt.R;
import com.todo.liszt.todoliszt.models.Category;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CategoryDetailFragment extends Fragment {

    Category mCategory;

    @Bind(R.id.categoryName) TextView mCategoryName;
    @Bind(R.id.categoryDescription) TextView mCategoryDescription;


    public CategoryDetailFragment() {
        // Required empty public constructor
    }

    public static CategoryDetailFragment newInstance(Category category) {
        CategoryDetailFragment categoryDetailFragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("category", Parcels.wrap(category));
        categoryDetailFragment.setArguments(args);
        return categoryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCategory = Parcels.unwrap(getArguments().getParcelable("category"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);
        ButterKnife.bind(this, view);

        //mCategoryName.setText(mCategory.getName());
        //mCategoryDescription.setText(mCategory.getDescription());

        //Log.d("CATEGORY", mCategory.getName());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
