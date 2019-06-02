package com.thewhiteunicorn.trip_manager.model;

import io.realm.RealmObject;

public class Location extends RealmObject {
    private String title;
    // geotag
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
