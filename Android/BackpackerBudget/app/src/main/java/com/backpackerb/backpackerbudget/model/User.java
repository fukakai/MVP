package com.backpackerb.backpackerbudget.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String id;
    private String network="network";
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    @Exclude
    private String password1;
    @Exclude
    private String password2;
    /**
     * Default constructor required for calls to DataSnapshot.getValue(User.class)
     */
    public User(){ }

    /**
     * Database user loading constructor with JsonPicture
     * @param id
     * @param network
     * @param firstname
     * @param lastname
     * @param email
     * @param picture
     */
    public User(    String id, String network, String firstname,
                    String lastname, String email, String picture) {
        this.id         = id;
        this.network    = network;
        this.firstname  = firstname;
        this.lastname   = lastname;
        this.email      = email;
        this.picture    = picture;
    }
    /**
     * Database user loading constructor for twitter
     * @param id
     * @param network
     * @param firstname
     * @param picture
     */
    public User(    String id, String network, String firstname, String picture) {
        this.id         = id;
        this.network    = network;
        this.firstname  = firstname;
        this.picture    = picture;
    }

    /** GETTERS */

    public String getId() {
        return id;
    }

    public String getNetwork() {
        return network;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullName(){
        return getFirstname() + " " + getLastname();
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    @Exclude
    public String getPassword1() {
        return password1;
    }

    @Exclude
    public String getPassword2() {
        return password2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    @Exclude
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "id: "+this.id+" network: "+this.network+" Fullname: "+this.firstname+" "+this.lastname+" email: "+this.email;
    }
}