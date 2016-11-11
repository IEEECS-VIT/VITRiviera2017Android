
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditEvent {

    @SerializedName("eventId")
    @Expose
    private String eventId;
    @SerializedName("changes")
    @Expose
    private Event changes;

    /**
     * 
     * @return
     *     The eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * 
     * @param eventId
     *     The eventId
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * 
     * @return
     *     The changes
     */
    public Event getChanges() {
        return changes;
    }

    /**
     * 
     * @param changes
     *     The changes
     */
    public void setChanges(Event changes) {
        this.changes = changes;
    }

}
