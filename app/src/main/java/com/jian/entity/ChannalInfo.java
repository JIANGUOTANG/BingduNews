package com.jian.entity;

/**
 * Created by jian on 2016/10/15.
 */

public class ChannalInfo {
    private String Title;
    private int imageId;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ChannalInfo(String title, int imageId) {

        Title = title;
        this.imageId = imageId;
    }
}
