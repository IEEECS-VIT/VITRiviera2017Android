
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Change {

    @SerializedName("changeField")
    @Expose
    public String changeField;
    @SerializedName("changeValue")
    @Expose
    public String changeValue;

}
