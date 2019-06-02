package com.thewhiteunicorn.trip_manager.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class StuffSet extends RealmObject {
    private boolean isTemplate;
    private String name;
    private Date creationDate;
    private RealmList<StuffItem> stuffItem;
}
