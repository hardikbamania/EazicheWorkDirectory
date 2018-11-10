package com.app.eaziche.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hardik on 07-11-2016.
 */
public class ShopItems implements Parcelable{

    private int uid;
    private String bio,name,address,lat,lon,rate,views,followers,logo;

    public ShopItems(int uid, String name, String bio, String address, String rate, String views, String followers, String logo) {
        this.uid = uid;
        this.bio = bio;
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.views = views;
        this.followers = followers;
        this.logo = logo;
    }

    private ShopItems(Parcel in) {
        uid = in.readInt();
        bio = in.readString();
        name = in.readString();
        address = in.readString();
        lat = in.readString();
        lon = in.readString();
        rate = in.readString();
        views = in.readString();
        followers = in.readString();
        logo = in.readString();
    }

    public static final Creator<ShopItems> CREATOR = new Creator<ShopItems>() {
        @Override
        public ShopItems createFromParcel(Parcel in) {
            return new ShopItems(in);
        }

        @Override
        public ShopItems[] newArray(int size) {
            return new ShopItems[size];
        }
    };

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }


    public void setbio(String bio) {
        this.bio = bio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getfollowers() {
        return followers;
    }

    public void setfollowers(String followers) {
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public String getbio() {
        return bio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(bio);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeString(rate);
        dest.writeString(views);
        dest.writeString(followers);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
