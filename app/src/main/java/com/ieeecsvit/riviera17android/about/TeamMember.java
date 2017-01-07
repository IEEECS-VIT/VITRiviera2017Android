package com.ieeecsvit.riviera17android.about;

/**
 * Created by root on 9/8/16.
 */
public class TeamMember {

    private String name;
    private String regno;
    private String gitid;
    private String link;
    private int imageResId;

    public TeamMember() {

    }

    public TeamMember(String name, String regno, int imageResId, String link) {
        this.name = name;
        this.regno = regno;
        this.imageResId = imageResId;
        this.link = link;
    }

    public String getLink() {return link;}

    public String getName() {
        return name;
    }

    public String getRegno() {
        return regno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) { this.link = link;}

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
