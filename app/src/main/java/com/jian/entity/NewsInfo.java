package com.jian.entity;

import com.jian.util.MyUtil;

import java.io.Serializable;

/**
 * Created by jian on 2016/10/15.
 */
public class NewsInfo implements Serializable {
    private String name;
    private String imgUrl;
    private String detail;
    private String tab;
    private int type = MyUtil.TYPE_BODY;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int commentCount;

    public NewsInfo(String name, String imgUrl, String detail, String tab, int commentCount, int type) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.detail = detail;
        this.tab = tab;
        this.type = type;
        this.commentCount = commentCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
