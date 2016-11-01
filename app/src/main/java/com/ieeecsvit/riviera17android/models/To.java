
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class To extends RealmObject{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("event_name")
    @Expose
    private String eventName;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * 
     * @param eventName
     *     The event_name
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
