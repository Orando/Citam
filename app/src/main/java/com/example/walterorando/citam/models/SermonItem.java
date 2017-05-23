package com.example.walterorando.citam.models;

/**
 * Created by Walter Orando on 4/15/2017.
 */

public class SermonItem {
    private int img;
    private String title, info;
    public SermonItem(){}

    public SermonItem(int img, String title, String info){
        this.img = img;
        this.title = title;
        this.info = info;
    }

    public SermonItem(int img, String title){
        this.img = img;
        this.title = title;
        this.info = "";
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

