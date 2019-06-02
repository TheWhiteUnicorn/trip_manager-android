package com.thewhiteunicorn.trip_manager.model;

import io.realm.RealmObject;

public class TravelGroup extends RealmObject {
    private String title;
    private String note;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
