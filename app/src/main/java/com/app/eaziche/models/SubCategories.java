package com.app.eaziche.models;

/**
 * Created by hardik on 18-10-2016.
 */

public class SubCategories {
    private int id;
    private String image;
    private String name,desc;

    public SubCategories(int id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public SubCategories(int id, String image, String name, String Desc) {
        this.id = id;
        this.image = image;
        this.name = name;
        desc = Desc;
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

    public String getDesc() {
        return desc;
    }
}
