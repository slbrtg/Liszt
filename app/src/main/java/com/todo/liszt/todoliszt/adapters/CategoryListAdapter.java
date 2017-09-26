package com.todo.liszt.todoliszt.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.todo.liszt.todoliszt.R;
import com.todo.liszt.todoliszt.models.Category;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

import java.util.ArrayList;

public class CategoryListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Category> mCategories = new ArrayList<>();

    public CategoryListAdapter(Context context, ArrayList<Category> categories) {
        this.mContext = context;
        this.mCategories = categories;
    }




    @Override
    public int getCount() {
        return mCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.category_list_grid_item, null);
            TextView categoryName = gridView.findViewById(R.id.categoryGridItem);
            categoryName.setText(mCategories.get(position).getName());
            Log.d("DOGHAT", "BUT NOT REALLY");
        } else {
            gridView = (View) convertView;
            Log.d("DOGHAT", "EMPTY!!!!!!!!!!!");
        }

        return gridView;
    }
}
