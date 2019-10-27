package com.backpackerb.backpackerbudget.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.LoginContract;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.backpackerb.backpackerbudget.view.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;


public class LoginFirebasePresenter implements LoginContract.LoginFirebasePresenter {

    private final LoginContract.view view;
    private final FirebaseAuth mAuth;
    private AuthStateListener mAuthListener;

    public LoginFirebasePresenter(LoginContract.view view) {
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean isLoggedIn(){
        boolean isLoggedIn = mAuth.getCurrentUser()!=null;
        return isLoggedIn;
    }

    @Override
    public void logOut(){
        if(isLoggedIn()) {
            FirebaseAuth.getInstance().signOut();
            mAuth.signOut();
        }
    }
    /**
     * LiveData
     */
    @Override
    public void dataUpdateListener(){
        view.setLoadingPanelVisible();
        mAuthListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() != null) {
                    // Signed in successfully, show authenticated UI.
                    Tools.changeActivity(view.getActivityContext(),HomeActivity.class);
                }
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * login method
     * @param email
     * @param password
     */

    public void signin(String email, String password) {
        view.setLoadingPanelVisible();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Tools.changeActivity(view.getActivityContext(), HomeActivity.class);
                        } else {
                            view.setLoadingPanelGone();
                            // If sign in fails, display a message to the user.
                            Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_firebase_signin_failed));
                        }
                    }
                });
    }
}
