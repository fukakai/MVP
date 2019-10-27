package com.backpackerb.backpackerbudget.presenter;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.LoginContract;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.backpackerb.backpackerbudget.view.HomeActivity;
import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import retrofit2.Call;

public class LoginTwitterPresenter implements LoginContract.LoginTwitterPresenter {

    private final LoginContract.view view;
    private final FirebaseAuth mAuth;

    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(Constants.TAG_FIREBASE_REALTIME_DATABASE_USERS);
    private com.backpackerb.backpackerbudget.model.User user;

    public LoginTwitterPresenter(LoginContract.view view) {
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean isLoggedIn(){
        boolean isLoggedIn = TwitterCore.getInstance().getSessionManager().getActiveSession()!=null;
        return isLoggedIn;
    }

    @Override
    public void logOut(){
        if(isLoggedIn()) {
            TwitterCore.getInstance().getSessionManager().clearActiveSession();
            FirebaseAuth.getInstance().signOut();
            mAuth.signOut();
        }
    }

    /**
     * Get Twitter User infos & push to Firebase Database
     */
    public void addUserToDatabase(){
        //Add twitter Account to Firebase Database
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        AccountService accountService = twitterApiClient.getAccountService();
        Call<User> call = accountService.verifyCredentials(true, true, true);
        call.enqueue(new Callback<com.twitter.sdk.android.core.models.User>() {
            @Override
            public void success(Result<com.twitter.sdk.android.core.models.User> result) {
                //here we go User details
                User resultUser = result.data;
                user = new com.backpackerb.backpackerbudget.model.User(
                        resultUser.idStr,Constants.TAG_TWITTER,resultUser.name,resultUser.profileImageUrl.replace("_normal","_bigger"));
            }

            @Override
            public void failure(TwitterException exception) {
                view.setLoadingPanelGone();
            }
        });
    }

    @Override
    public Callback<TwitterSession> callback(){
        return new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with  result, which provides a TwitterSession for making API calls
                view.setLoadingPanelVisible();

                addUserToDatabase();
                handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                view.setLoadingPanelGone();
                Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_twitter_signin));
            }
        };
    }

    @Override
    public void handleTwitterSession(TwitterSession session) {
        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Save Facebook User Properties in Database
                            database.child(mAuth.getUid()).setValue(user);
                            // Sign in success, update UI with the signed-in user's information
                            Tools.changeActivity(view.getActivityContext(),HomeActivity.class);
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            view.setLoadingPanelGone();
                            // If sign in fails, display a message to the user.
                            Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_twitter_signin));
                            //updateUI(null);
                        }

                        // ...
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
        if(requestCode == 140) {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
            // Pass the activity result to the Twitter login button.
            view.getTwitterButton().onActivityResult(requestCode, resultCode, data);
        }
    }
}
