package com.todo.liszt.todoliszt.models;

import org.parceler.Parcel;

@Parcel
public class Task {

    String name;
    String description;

    public Task() { }

    public Task(String name, String description) {
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