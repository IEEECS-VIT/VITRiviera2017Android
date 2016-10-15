
package com.ieeecsvit.riviera17android.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Events {

    @SerializedName("events")
    @Expose
    public List<Event> events = new ArrayList<>();
    @SerializedName("success")
    @Expose
    public Boolean success;

}
