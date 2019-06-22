package com.example.myapplication.Model;

public class User {

    public String username, category, description, userid;

    public User(String username, String category, String description, String userid) {
        this.username = username;
        this.category = category;
        this.description = description;
        this.userid = userid;
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
}
