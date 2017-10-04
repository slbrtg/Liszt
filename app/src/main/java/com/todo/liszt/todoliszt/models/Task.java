package com.todo.liszt.todoliszt.models;

import org.parceler.Parcel;

@Parcel
public class Task {

    String name;
    String description;
    String catId;

    public Task() { }

    public Task(String name, String description, String catId) {
        this.name = name;
        this.description = description;
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCatId() {
        return catId;
    }



}