package com.thewhiteunicorn.trip_manager.model;

import java.util.Date;

import io.realm.RealmObject;

public class Travel extends RealmObject {
    private long id;
    private String title;
    private TravelGroup travelGroup;
    private Date dispatchDate;
    private Date arriveDate;
    private Location startPoint;
    private Location destinationPoint;
    private String note;
    private StuffSet stuffSet;
    // status


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

    public TravelGroup getTravelGroup() {
        return travelGroup;
    }

    public void setTravelGroup(TravelGroup travelGroup) {
        this.travelGroup = travelGroup;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Location getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Location startPoint) {
        this.startPoint = startPoint;
    }

    public Location getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(Location destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StuffSet getStuffSet() {
        return stuffSet;
    }

    public void setStuffSet(StuffSet stuffSet) {
        this.stuffSet = stuffSet;
    }
}
