package com.example.foodbay;

public class User {

    public String id;
    public String username;
    public String email;
    public String password;
    public String confirmpw;
    //public Integer contact;
    String contact;

    public User(){

    }


   public User(String username, String email, String password, String confirmpw, String contact) {
        //this.id = userid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmpw = confirmpw;
        this.contact = contact;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpw() {
        return confirmpw;
    }

    public void setConfirmpw(String confirmpw) {
        this.confirmpw = confirmpw;
    }

   /* public Integer getContact(String trim) {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}

