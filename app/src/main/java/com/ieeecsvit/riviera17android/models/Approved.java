
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Approved {

    @SerializedName("by")
    @Expose
    public String by;
    @SerializedName("approvalStatus")
    @Expose
    public Boolean approvalStatus;

}
