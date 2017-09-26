package com.todo.liszt.todoliszt.models;

import org.parceler.Parcel;

@Parcel
public class Category {

    String name;
    String description;

    public Category() { }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



}
