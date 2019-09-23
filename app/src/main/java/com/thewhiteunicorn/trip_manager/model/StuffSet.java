package com.thewhiteunicorn.trip_manager.model;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class StuffSet extends RealmObject {
    private long id;
    private boolean isTemplate;
    private String name;
    private Date creationDate;
    private RealmList<StuffInSet> stuffItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StuffSet () {
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

    public RealmList<StuffInSet> getStuffItems() {
        return stuffItems;
    }

    public void setStuffItems(RealmList<StuffInSet> stuffItems) {
        this.stuffItems = stuffItems;
    }
}
