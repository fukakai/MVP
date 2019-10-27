package com.backpackerb.backpackerbudget.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.LoginContract;
import com.backpackerb.backpackerbudget.model.User;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.backpackerb.backpackerbudget.view.HomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginGooglePresenter implements LoginContract.LoginGooglePresenter{

    private final LoginContract.view view;
    private final FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(Constants.TAG_FIREBASE_REALTIME_DATABASE_USERS);
    private User user;

    public LoginGooglePresenter(LoginContract.view view) {
        this.view = view;
        mAuth = FirebaseAuth.getInstance();

        this.configureWebClient();
    }

    @Override
    public boolean isLoggedIn(){
        Boolean isLoggedIn = view.returnLastSignedAccount() !=null;
        return isLoggedIn;
    }

    @Override
    public void logOut(){
        if(isLoggedIn()) {
            mGoogleSignInClient.signOut();
            mAuth.signOut();
            if (!isLoggedIn()) {
                Log.d(Constants.LOG_GOOGLE, Constants.GOOGLE_LOG_OUT);
            }
        }
    }

    @Override
    public void configureWebClient(){
        String webClientId = view.getDefaultWebClientId();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(webClientId)
                .requestEmail()
                .build();

        mGoogleSignInClient = view.returnClient(gso);
    }

    /**
     * Google
     * Onclick event button call this method first
     */
    @Override
    public void signIn() {
        view.setLoadingPanelVisible();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        view.changeActivityForResult(signInIntent);
    }

    /**
     * Google
     * Handle Signin Result
     * @param completedTask
     */
    @Override
    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            //Save Facebook User Properties in Database
            database.child(mAuth.getUid()).setValue(user);

            // Signed in successfully, show authenticated UI.
            // Redirect to HomeActivity
            Tools.changeActivity(view.getActivityContext(),HomeActivity.class,Constants.GOOGLE_LOG_IN);
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            // Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());

            view.setLoadingPanelGone();
            Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_google_firebase_signin_failed));
        }
    }
    /**
     * Google
     * Add Google Account to Firebase User Database !
     * @param acct
     */
    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            GoogleSignInAccount acct = view.returnLastSignedAccount();

                            if (acct != null) {
                                String personGivenName = acct.getGivenName();
                                String personFamilyName = acct.getFamilyName();
                                String personEmail = acct.getEmail();
                                String personId = acct.getId();
                                String personPhoto = acct.getPhotoUrl().toString();

                                user = new User(personId,Constants.TAG_GOOGLE,personGivenName,personFamilyName,personEmail,personPhoto);
                                //Save Google User Properties in Database
                                database.child(mAuth.getUid()).setValue(user);
                            }

                            Tools.changeActivity(view.getActivityContext(),HomeActivity.class);
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            view.setLoadingPanelGone();
                            // If sign in fails, display a message to the user.
                            Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_google_signin_failed));
                            //updateUI(null);
                        }
                    }
                });
    }

    /**
     * Google
     * Check Account
     */
    @Override
    public void checkGoogleAccount(){
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = view.returnLastSignedAccount();
        if(account == null){
            Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_google_login_failed));
        }else{
            //user has logged in
            //we don't want the user to stay on this page
            view.setLoadingPanelGone();
        }
    }

    /**
     * OnActivityResult Call
     * Pass the activity result back to the Facebook SDK
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
        public void doOnActivityResult(int requestCode, int resultCode, Intent data){
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == Constants.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                this.firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                view.setLoadingPanelGone();
                // ...
            }
        }
    }
}
