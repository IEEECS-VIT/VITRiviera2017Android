
package com.ieeecsvit.riviera17android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MessagesResponse {

    @SerializedName("data")
    @Expose
    private List<Message> data = new ArrayList<Message>();
    @SerializedName("success")
    @Expose
    private Boolean success;

    /**
     * 
     * @return
     *     The data
     */
    public List<Message> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<Message> data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
