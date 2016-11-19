
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptRequest {

    @SerializedName("requestId")
    @Expose
    public String requestId;
    @SerializedName("accept")
    @Expose
    public Boolean accept;

}
