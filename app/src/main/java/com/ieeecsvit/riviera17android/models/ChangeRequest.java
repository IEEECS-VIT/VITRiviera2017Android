
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChangeRequest {

    @SerializedName("eventId")
    @Expose
    public Long eventId;
    @SerializedName("changes")
    @Expose
    public List<Change> changes = new ArrayList<Change>();

}
