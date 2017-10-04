package com.todo.liszt.todoliszt.models;

import org.parceler.Parcel;

@Parcel
public class Category {

    String name;
    String description;
    String pushId;

    public Category() { }

    public Category(String name, String description, String pushId) {
        this.name = name;
        this.description = description;
        this.pushId = pushId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }



}
