package com.ieeecsvit.riviera17android;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.Message;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    private RealmController(Application application) {
        Realm.init(application);

        realm = Realm.getInstance(RealmConfig.getInstance());
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }


    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {
        realm.waitForChange();
    }

    //clear all objects from Book.class
    public void clearAllEvents() {
        realm.beginTransaction();
        realm.delete(Event.class);
        realm.commitTransaction();
    }

    public void clearAllMessages() {
        realm.beginTransaction();
        realm.delete(Message.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<Event> getEvents(String category) {
        return realm.where(Event.class)
                .equalTo("eventCategory",category)
                .findAll();
    }

    public RealmResults<Event> getSubEvents(String category, String subCategory) {

        if(subCategory.equals("Others")){
            subCategory = null;
        }

        return realm.where(Event.class)
                .equalTo("eventCategory",category)
                .equalTo("eventSubcategory",subCategory)
                .findAll();
    }

    public RealmResults<Event> getEvents(){
        return realm.where(Event.class).findAll();
    }

    public Event getEvent(String eventId){
        return realm.where(Event.class).equalTo("id", eventId).findFirst();
    }

    public RealmResults<Message> getMessages(){
        return realm.where(Message.class).findAll();
    }

    public boolean hasEvents() {
        return !realm.where(Event.class).findAll().isEmpty();
    }

    void setFavourite(String eventId, Boolean favourite){
        Event event = realm.where(Event.class).equalTo("id",eventId).findFirst();
        realm.beginTransaction();
        event.checked = favourite;
        realm.commitTransaction();
    }
}