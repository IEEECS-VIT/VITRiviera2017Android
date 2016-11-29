
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("eventId")
    @Expose
    public String eventId;
}
