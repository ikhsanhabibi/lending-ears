package com.example.myapplication.Model;

public class User {

    public String username, category, description, userid, profileimageUrl;

    public User(String username, String category, String description, String userid, String profileimageUrl) {
        this.username = username;
        this.category = category;
        this.description = description;
        this.userid = userid;
        this.profileimageUrl = profileimageUrl;
    }

    public  User(){

    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProfileimageUrl() {
        return profileimageUrl;
    }

    public void setProfileimageUrl(String profileimageUrl) { this.profileimageUrl = profileimageUrl; }
}
