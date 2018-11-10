package com.app.eaziche.models;

import android.graphics.drawable.Drawable;

/**
 * Created by hardik on 18-10-2016.
 */

public class Images {
    private int id;
    private String image;
    private String name;

    public Images(int id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public Images() {
        image ="";
        name = "Hello World";
        id = 1;
    }

    public int getId() {
        return id;
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

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
