package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventCoordinator {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("reg_no")
    @Expose
    public String regNo;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone")
    @Expose
    public String phone;

}
