package com.todo.liszt.todoliszt.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.todo.liszt.todoliszt.Constants;
import com.todo.liszt.todoliszt.R;
import com.todo.liszt.todoliszt.models.Category;
import com.todo.liszt.todoliszt.ui.CategoryDetailActivity;

import org.parceler.Parcels;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FirebaseCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseCategoryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCategory(Category category) {
        TextView categoryGridItem = (TextView) mView.findViewById(R.id.categoryGridItem);

        categoryGridItem.setText(category.getName());
    }

    @Override
    public void onClick(View view) {

        if (view == itemView) {

            final ArrayList<Category> categories = new ArrayList<>();
            DatabaseReference ref = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CATEGORY);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        categories.add(snapshot.getValue(Category.class));
                    }

                    int itemPosition = getLayoutPosition();
                    Category category = categories.get(itemPosition);
                    Intent intent = new Intent(mContext, CategoryDetailActivity.class);
                    intent.putExtra("category", Parcels.wrap(category));
                    mContext.startActivity(intent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

}
