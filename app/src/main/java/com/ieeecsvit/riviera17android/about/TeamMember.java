package com.ieeecsvit.riviera17android.about;

/**
 * Created by root on 9/8/16.
 */
public class TeamMember {

    private String name;
    private int imageResId;

    public TeamMember(){

    }

    public TeamMember(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
