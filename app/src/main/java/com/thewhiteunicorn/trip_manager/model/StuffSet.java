package com.thewhiteunicorn.trip_manager.model;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class StuffSet extends RealmObject {
    private boolean isTemplate;
    private String name;
    private Date creationDate;
    private RealmList<StuffItem> stuffItem;

    StuffSet () {
        creationDate = Calendar.getInstance().getTime();
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setTemplate(boolean template) {
        isTemplate = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public RealmList<StuffItem> getStuffItem() {
        return stuffItem;
    }

    public void setStuffItem(RealmList<StuffItem> stuffItem) {
        this.stuffItem = stuffItem;
    }
}
