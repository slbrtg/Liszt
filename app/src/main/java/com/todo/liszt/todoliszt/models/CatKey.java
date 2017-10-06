package com.todo.liszt.todoliszt.models;

import org.parceler.Parcel;

// Class holds the key to find a category
// Gets pushed to the user's node
@Parcel
public class CatKey {
    String catKey;

    public CatKey () {}

    public CatKey(String catKey) {
        this.catKey = catKey;
    }

    public String getCatKey() {
        return catKey;
    }

}
