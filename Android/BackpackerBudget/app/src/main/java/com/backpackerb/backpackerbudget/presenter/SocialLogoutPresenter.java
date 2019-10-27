package com.backpackerb.backpackerbudget.presenter;

import android.util.Log;

import com.backpackerb.backpackerbudget.contract.LoginContract;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.backpackerb.backpackerbudget.view.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SocialLogoutPresenter implements LoginContract.SocialLogoutPresenter {
    private final LoginContract.view view;
    private FirebaseAuth mAuth;

    public static Boolean STATIC_LOGOUT = false;

    public SocialLogoutPresenter(LoginContract.view view) {
        this.view = view;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void logOutOrRedirect(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(STATIC_LOGOUT){
            logOutSocial();
        }else if(!STATIC_LOGOUT && (user!=null)){
            Tools.changeActivity(view.getActivityContext(),HomeActivity.class);
        }else{
            //Log.w(Constants.LOG_SOCIALLOGOUT,"static logout=false && user is null");
        }
    }

    @Override
    public void showSocialLoginState(){
        Log.i(Constants.LOG_SOCIALLOGOUT,"---------------------------------------------------");
        Log.i(Constants.LOG_SOCIALLOGOUT,"Firebase: "+view.getLoginFirebasePresenter().isLoggedIn());
        Log.i(Constants.LOG_SOCIALLOGOUT,"Google: "+view.getLoginGooglePresenter().isLoggedIn());
        Log.i(Constants.LOG_SOCIALLOGOUT,"Facebook: "+view.getLoginFacebookPresenter().isLoggedIn());
        Log.i(Constants.LOG_SOCIALLOGOUT,"Twitter: "+view.getLoginTwitterPresenter().isLoggedIn());
        Log.i(Constants.LOG_SOCIALLOGOUT,"---------------------------------------------------");
    }

    @Override
    public boolean logOutSocial(){
        this.showSocialLoginState();
        view.getLoginFirebasePresenter().logOut();
        view.getLoginFacebookPresenter().logOut();
        view.getLoginGooglePresenter().logOut();
        view.getLoginTwitterPresenter().logOut();

        this.showSocialLoginState();
        staticLogOutOff();
        return true;
    }

    public static void staticLogOutOn(){
        STATIC_LOGOUT = true;
    }

    public static void staticLogOutOff(){
        STATIC_LOGOUT = false;
    }
}
