
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerEventResponse {

    @SerializedName("event")
    @Expose
    public Event event;
    @SerializedName("success")
    @Expose
    public Boolean success;

}
