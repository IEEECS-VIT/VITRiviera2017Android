
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChangeResponse {

    @SerializedName("data")
    @Expose
    public List<ChangeRequests> data = new ArrayList<ChangeRequests>();
    @SerializedName("success")
    @Expose
    public Boolean success;

}
