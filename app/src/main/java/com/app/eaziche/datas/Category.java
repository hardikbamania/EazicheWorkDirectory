package com.app.eaziche.datas;

import android.graphics.drawable.Drawable;

/**
 * Created by hardik on 18-10-2016.
 */

public class Category {
    int id;
    Drawable image;
    String name;

    public Category(int id, Drawable image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public Category() {
        image =null;
        name = "Hello World";
        id = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
