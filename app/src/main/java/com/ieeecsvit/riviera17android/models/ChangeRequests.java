
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChangeRequests {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("eventFor")
    @Expose
    public Event eventFor;
    @SerializedName("__v")
    @Expose
    public Integer v;
    @SerializedName("approved")
    @Expose
    public Approved approved;
    @SerializedName("changes")
    @Expose
    public List<Change> changes = new ArrayList<Change>();

}
