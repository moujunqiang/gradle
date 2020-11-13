package com.mmrx.health.bean;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lidroid.xutils.db.annotation.Id;

import java.io.Serializable;

public class News implements Serializable {

    @Id
    private int id;
    private String title;
    private String subtitle;
    private String content;
    private int imageID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public News() {
    }

    public News(String title, String subtitle, String content, int imageID) {
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.imageID = imageID;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
