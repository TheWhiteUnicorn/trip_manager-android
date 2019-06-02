package com.thewhiteunicorn.trip_manager.model;

import io.realm.RealmObject;

public class Stuff extends RealmObject {
    private String title;
    private String description;
    // category
    private Location location;
}
