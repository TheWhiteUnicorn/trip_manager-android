package com.thewhiteunicorn.trip_manager.model;

import io.realm.RealmObject;

public class StuffItem extends RealmObject {
    private long id;
    private String title;
    private String description;
    // category
    private Location location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}
