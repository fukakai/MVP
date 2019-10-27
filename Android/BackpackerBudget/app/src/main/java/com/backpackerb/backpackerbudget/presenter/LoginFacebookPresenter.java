package com.backpackerb.backpackerbudget.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.LoginContract;
import com.backpackerb.backpackerbudget.model.User;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.backpackerb.backpackerbudget.view.HomeActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFacebookPresenter implements LoginContract.LoginFacebookPresenter {

    private final LoginContract.view view;
    private final FirebaseAuth mAuth;

    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(Constants.TAG_FIREBASE_REALTIME_DATABASE_USERS);
    private User user;

    public LoginFacebookPresenter(LoginContract.view view) {
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean isLoggedIn(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        return isLoggedIn;
    }

    @Override
    public void logOut(){
        if(isLoggedIn()) {
            if (AccessToken.getCurrentAccessToken() != null) {
                LoginManager.getInstance().logOut();
            }
        }
    }

    @Override
    public FacebookCallback<LoginResult> callback(){
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Save Facebook infos in database
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String id       = String.valueOf(object.getString(Constants.FACEBOOK_USER_ID));
                                    String firstname= String.valueOf(object.getString(Constants.FACEBOOK_USER_FIRSTNAME));
                                    String lastname = String.valueOf(object.getString(Constants.FACEBOOK_USER_LASTNAME));
                                    String email    = String.valueOf(object.getString(Constants.FACEBOOK_USER_EMAIL));
                                    String picture  = String.valueOf(object.getString(Constants.FACEBOOK_USER_PICTURE));
                                    user = new User(id,Constants.TAG_FACEBOOK,firstname,lastname,email,picture);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString(   Constants.FACEBOOK_FIELDS,
                                        Constants.FACEBOOK_USER_ID          +","+
                                        Constants.FACEBOOK_USER_FIRSTNAME   +","+
                                        Constants.FACEBOOK_USER_LASTNAME    +","+
                                        Constants.FACEBOOK_USER_EMAIL       +","+
                                        Constants.FACEBOOK_USER_PICTURE);
                request.setParameters(parameters);
                request.executeAsync();

                //handle facebook token
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                view.setLoadingPanelGone();
            }

            @Override
            public void onError(FacebookException error) {
                view.setLoadingPanelGone();
                Log.i(Constants.LOG_FACEBOOK,error.getMessage());
            }
        };
    }

    @Override
    public void handleFacebookAccessToken(AccessToken token) {
        //signin with credentials to Firebase
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Save Facebook User Properties in Database
                            database.child(mAuth.getUid()).setValue(user);

                            // Sign in success, update UI with the signed-in user's information
                            Tools.changeActivity(view.getActivityContext(),HomeActivity.class);
                        } else {
                            view.setLoadingPanelGone();
                            // If sign in fails, display a message to the user.
                            Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_facebook_login_failed));
                        }
                    }
                });
    }

    /**
     * OnActivityResult Call
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void doOnActivityResult(CallbackManager mCallbackManager,int requestCode, int resultCode, Intent data){
        if(requestCode == 64206) {
            // Pass the activity result back to the Facebook SDK
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
