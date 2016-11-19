
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Approved {

    @SerializedName("by")
    @Expose
    public Object by;
    @SerializedName("approvalStatus")
    @Expose
    public Object approvalStatus;

}
