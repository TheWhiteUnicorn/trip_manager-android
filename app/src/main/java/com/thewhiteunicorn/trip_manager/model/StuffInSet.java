package com.thewhiteunicorn.trip_manager.model;

import io.realm.RealmObject;

public class StuffInSet extends RealmObject {
    private long id;
    private StuffItem stuffItem;
    private long qty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public StuffItem getStuffItem() {
        return stuffItem;
    }

    public void setStuffItem(StuffItem stuffItem) {
        this.stuffItem = stuffItem;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }
}
