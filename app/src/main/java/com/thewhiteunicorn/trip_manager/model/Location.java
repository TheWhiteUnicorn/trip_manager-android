package com.thewhiteunicorn.trip_manager.model;

import io.realm.RealmObject;

public class Location extends RealmObject {
    private String title;
    // geotag
    private String description;
}
