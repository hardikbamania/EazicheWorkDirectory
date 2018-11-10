package com.app.eaziche.models;

/**
 * Created by hardik on 18-10-2016.
 */

public class Rates {
    private int id;
    private String image;
    private String name,comment;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    float rate;

    public Rates(int id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public Rates(int id, String image, String name, String comment,float rate) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.comment = comment;
        this.rate = rate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

}
