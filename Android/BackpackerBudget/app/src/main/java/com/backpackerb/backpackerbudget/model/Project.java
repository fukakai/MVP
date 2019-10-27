package com.backpackerb.backpackerbudget.model;

import com.google.firebase.database.IgnoreExtraProperties;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Project {

    private String id_user;
    private String title;
    /**
     * Default constructor required for calls to DataSnapshot.getValue(User.class)
     */
    public Project(){ }

    /**
     * Database user loading constructor with JsonPicture
     * @param id_user
     * @param title
     */
    public Project(String id_user, String title) {
        this.id_user    = id_user;
        this.title      = title;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** GETTERS */

    @Override
    public String toString() {
        return this.title;
    }
}