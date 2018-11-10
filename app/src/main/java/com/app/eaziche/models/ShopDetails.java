package com.app.eaziche.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hardik on 09-12-2016.
 */
public class ShopDetails implements Parcelable{

    private String id;
    private String name;
    private String user_name;
    private String website;
    private String address;
    private String lat;
    private String lon;
    private String city_id;
    private String contact_1;
    private String contact_2;
    private String email;
    private String logo;
    private String cover;
    private String bio;
    private float rates;
    private String no_of_rates;
    private String no_of_view;
    private String followers;
    private String our_service;
    private String status;

    public ShopDetails(String id, String name, String user_name, String website, String address, String lat, String lon, String city_id, String contact_1, String contact_2, String email, String logo, String cover, String bio, String rates, String no_of_rates, String no_of_view, String followers, String our_service) {
        this.id = id;
        this.name = name;
        this.user_name = user_name;
        this.website = website;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.city_id = city_id;
        this.contact_1 = contact_1;
        this.contact_2 = contact_2;
        this.email = email;
        this.logo = logo;
        this.cover = cover;
        this.bio = bio;
        this.rates = Float.parseFloat(rates);
        this.no_of_rates = no_of_rates;
        this.no_of_view = no_of_view;
        this.followers = followers;
        this.our_service = our_service;
        //this.status = status;
    }

    public ShopDetails(Parcel in) {
        id = in.readString();
        name = in.readString();
        user_name = in.readString();
        website = in.readString();
        address = in.readString();
        lat = in.readString();
        lon = in.readString();
        city_id = in.readString();
        contact_1 = in.readString();
        contact_2 = in.readString();
        email = in.readString();
        logo = in.readString();
        cover = in.readString();
        bio = in.readString();
        rates = in.readFloat();
        no_of_rates = in.readString();
        no_of_view = in.readString();
        followers = in.readString();
        our_service = in.readString();
        status = in.readString();
    }

    public static final Creator<ShopDetails> CREATOR = new Creator<ShopDetails>() {
        @Override
        public ShopDetails createFromParcel(Parcel in) {
            return new ShopDetails(in);
        }

        @Override
        public ShopDetails[] newArray(int size) {
            return new ShopDetails[size];
        }
    };

    public ShopDetails() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getContact_1() {
        return contact_1;
    }

    public void setContact_1(String contact_1) {
        this.contact_1 = contact_1;
    }

    public String getContact_2() {
        return contact_2;
    }

    public void setContact_2(String contact_2) {
        this.contact_2 = contact_2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Float getRates() {
        return rates;
    }

    public void setRates(Float rates) {
        this.rates = rates;
    }

    public String getNo_of_rates() {
        return no_of_rates;
    }

    public void setNo_of_rates(String no_of_rates) {
        this.no_of_rates = no_of_rates;
    }

    public String getno_of_view() {
        return no_of_view;
    }

    public void setno_of_view(String no_of_view) {
        this.no_of_view = no_of_view;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getOur_service() {
        return our_service;
    }

    public void setOur_service(String our_service) {
        this.our_service = our_service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(user_name);
        dest.writeString(website);
        dest.writeString(address);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeString(city_id);
        dest.writeString(contact_1);
        dest.writeString(contact_2);
        dest.writeString(email);
        dest.writeString(logo);
        dest.writeString(cover);
        dest.writeString(bio);
        dest.writeFloat(rates);
        dest.writeString(no_of_rates);
        dest.writeString(no_of_view);
        dest.writeString(followers);
        dest.writeString(our_service);
        dest.writeString(status);
    }
}
