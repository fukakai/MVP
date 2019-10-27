package com.backpackerb.backpackerbudget.utils;

/**
 * Project Constants
 */
public interface Constants {
    public static final String TAG_FIREBASE_REALTIME_DATABASE_USERS = "users";
    public static final String TAG_FIREBASE_REALTIME_DATABASE_PROJECTS = "projects";
    public static final String TAG_GOOGLE   = "GOOGLE";
    public static final String TAG_FACEBOOK = "FACEBOOK";
    public static final String TAG_TWITTER  = "TWITTER";
    public static final String TAG_FIREBASE = "FIREBASE";
    public static final String TAG_REGISTER = "REGISTER";
    public static final String TAG_HOME     = "HOME";
    public static final String TAG_SOCIALLOGOUT = "SOCIALLOGOUT";
    public static final String TAG_TOOLS    = "TOOLS";

    public static final String FACEBOOK_FIELDS          = "fields";
    public static final String FACEBOOK_USER_ID         = "id";
    public static final String FACEBOOK_USER_FIRSTNAME  = "first_name";
    public static final String FACEBOOK_USER_LASTNAME   = "last_name";
    public static final String FACEBOOK_USER_EMAIL      = "email";
    public static final String FACEBOOK_USER_PICTURE    = "picture";
    public static final String FACEBOOK_READ_PERMISSION_EMAIL = "email";
    public static final String FACEBOOK_READ_PERMISSION_PUBLICPROFILE = "public_profile";

    //////////////////////////////// - LOGS - //////////////////////////////////////////////////////

    //LOGS
    public static final String TAG_LOG_BB       = "LOGBB-";
    public static final String LOG_GOOGLE       = TAG_LOG_BB+TAG_GOOGLE;
    public static final String LOG_FACEBOOK     = TAG_LOG_BB+TAG_FACEBOOK;
    public static final String LOG_TWITTER      = TAG_LOG_BB+TAG_TWITTER;
    public static final String LOG_FIREBASE     = TAG_LOG_BB+TAG_FIREBASE;
    public static final String LOG_REGISTER     = TAG_LOG_BB+TAG_REGISTER;
    public static final String LOG_HOME         = TAG_LOG_BB+TAG_HOME;
    public static final String LOG_SOCIALLOGOUT = TAG_LOG_BB+TAG_SOCIALLOGOUT;
    public static final String LOG_TOOLS        = TAG_LOG_BB+TAG_TOOLS;

    //Google
    public static final String GOOGLE_LOG_OUT = "Google Log Out";
    public static final String GOOGLE_LOG_IN = "Google Log In";
    public static final Integer RC_SIGN_IN = 100; //Random number

}
