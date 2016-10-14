package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventRating {

    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("rating")
    @Expose
    public String rating;
    @SerializedName("comments")
    @Expose
    public String comments;

}
