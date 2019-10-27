package com.backpackerb.backpackerbudget.contract;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Button;

import com.backpackerb.backpackerbudget.presenter.LoginFacebookPresenter;
import com.backpackerb.backpackerbudget.presenter.LoginFirebasePresenter;
import com.backpackerb.backpackerbudget.presenter.LoginGooglePresenter;
import com.backpackerb.backpackerbudget.presenter.LoginTwitterPresenter;
import com.backpackerb.backpackerbudget.presenter.SocialLogoutPresenter;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public interface LoginContract {

    interface view{
        LoginFirebasePresenter getLoginFirebasePresenter();
        LoginFacebookPresenter getLoginFacebookPresenter();
        LoginGooglePresenter getLoginGooglePresenter();
        LoginTwitterPresenter getLoginTwitterPresenter();

        GoogleSignInClient returnClient(GoogleSignInOptions gso);
        GoogleSignInAccount returnLastSignedAccount();
        String getDefaultWebClientId();
        Context getActivityContext();
        void changeActivityForResult(Intent signInIntent);
        TwitterLoginButton getTwitterButton();

        void setLoadingPanelVisible();
        void setLoadingPanelGone();
    }

    interface LoginFirebasePresenter {
        boolean isLoggedIn();
        void logOut();
        void dataUpdateListener();
    }

    interface LoginFacebookPresenter {
        boolean isLoggedIn();
        void logOut();
        FacebookCallback<LoginResult> callback();
        void handleFacebookAccessToken(AccessToken token);
        void doOnActivityResult(CallbackManager mCallbackManager, int requestCode, int resultCode, Intent data);
    }

    interface LoginGooglePresenter {
        boolean isLoggedIn();
        void logOut();
        void configureWebClient();
        void signIn();
        void handleSignInResult(Task<GoogleSignInAccount> completedTask);
        void firebaseAuthWithGoogle(GoogleSignInAccount acct);
        void checkGoogleAccount();
        void doOnActivityResult(int requestCode, int resultCode, Intent data);
    }

    interface LoginTwitterPresenter{
        boolean isLoggedIn();
        void logOut();
        Callback<TwitterSession> callback();
        void handleTwitterSession(TwitterSession session);
        void doOnActivityResult(CallbackManager mCallbackManager,int requestCode, int resultCode, Intent data);
    }

    interface SocialLogoutPresenter{
        void showSocialLoginState();
        boolean logOutSocial();
        void logOutOrRedirect();
    }

    interface model{
        String getUserInfos();
    }
}