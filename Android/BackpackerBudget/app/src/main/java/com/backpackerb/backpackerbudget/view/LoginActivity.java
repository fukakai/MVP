package com.backpackerb.backpackerbudget.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.LoginContract;
import com.backpackerb.backpackerbudget.presenter.*;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import com.backpackerb.backpackerbudget.utils.Constants;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginActivity extends AppCompatActivity implements LoginContract.view {
    public LoginFirebasePresenter loginFirebasePresenter;
    public LoginFacebookPresenter loginFacebookPresenter;
    public LoginGooglePresenter loginGooglePresenter;
    public LoginTwitterPresenter loginTwitterPresenter;
    public SocialLogoutPresenter socialLogoutPresenter;

    private TextInputLayout mEmailFieldLayout;
    private TextInputEditText mEmailField;
    private String mEmailFieldText;
    private TextInputLayout mPasswordFieldLayout;
    private TextInputEditText mPasswordField;
    private String mPasswordFieldText;

    private CallbackManager mCallbackManager;
    private SocialOnClickClass socialOnClickclass;

    private Button mLoginFirebaseButton;
    private Button mRegisterLoginButton;
    private SignInButton mLoginGoogleButton;
    private LoginButton mLoginFacebookButton;
    private TwitterLoginButton mLoginTwitterButton;
    private RelativeLayout mLoadingPanel;

    private boolean emailError;
    private boolean passwordError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Twitter initialize
        Twitter.initialize(this);

        setContentView(R.layout.activity_login);
        Log.w("LOGBB-LOGINACTIVITY","oncreate()");

        //Presenters
        loginFirebasePresenter = new LoginFirebasePresenter(this);
        loginFacebookPresenter = new LoginFacebookPresenter(this);
        loginGooglePresenter = new LoginGooglePresenter(this);
        loginTwitterPresenter = new LoginTwitterPresenter(this);
        socialLogoutPresenter = new SocialLogoutPresenter(this);

        //Firebase & Facebook
        mCallbackManager = CallbackManager.Factory.create();

        //UsedMethods
        socialOnClickclass = new SocialOnClickClass();

        //Buttons
        mLoginGoogleButton   = findViewById(R.id.mGoogleButtonID);
        mLoginFirebaseButton = findViewById(R.id.mFirebaseButtonID);
        mLoginFacebookButton = findViewById(R.id.mFacebookButtonID);
        mRegisterLoginButton = findViewById(R.id.mRegisterLoginButton);
        mLoginTwitterButton  = findViewById(R.id.mTwitterButtonID);
        mLoadingPanel        = findViewById(R.id.loadingPanel);

        mEmailFieldLayout   = findViewById(R.id.emailFieldLayout);
        mPasswordFieldLayout= findViewById(R.id.passwordFieldLayout);
        mEmailField         = findViewById(R.id.emailField);
        mPasswordField      = findViewById(R.id.passwordField);

        //Facebook setters
        mLoginFacebookButton.setReadPermissions(Constants.FACEBOOK_READ_PERMISSION_EMAIL, Constants.FACEBOOK_READ_PERMISSION_PUBLICPROFILE);

        //Onclick & Callbacks
        mLoginGoogleButton.setOnClickListener(socialOnClickclass);
        mLoginFirebaseButton.setOnClickListener(socialOnClickclass);
        mLoginFacebookButton.setOnClickListener(socialOnClickclass);
        mLoginFacebookButton.registerCallback(mCallbackManager,loginFacebookPresenter.callback());
        mRegisterLoginButton.setOnClickListener(socialOnClickclass);
        mLoginTwitterButton.setCallback(loginTwitterPresenter.callback());

        //else we stock locally the user to use the informations
        Tools.checkIfUserIsConnected(getActivityContext(),this.getClass().toString(),HomeActivity.class);
    }

    @Override
    protected void onResume() {
        socialLogoutPresenter.logOutOrRedirect();
        super.onResume();
    }


    /**
     * Onclick Class for MVP Refactoring
     */
    public class SocialOnClickClass implements View.OnClickListener{
        @Override
        public void onClick(final View view){
            mEmailFieldText = mEmailField.getText().toString();
            mPasswordFieldText = mPasswordField.getText().toString();

            switch (view.getId()){
                case R.id.mFirebaseButtonID: if(checkFields()){loginFirebasePresenter.signin(mEmailFieldText,mPasswordFieldText);} break;
                case R.id.mGoogleButtonID: loginGooglePresenter.signIn(); break;
                case R.id.mFacebookButtonID: setLoadingPanelVisible(); break;
                case R.id.mRegisterLoginButton: Tools.changeActivity(getApplicationContext(),RegisterActivity.class); break;
            }
        }
    }

    public boolean checkFields(){
        emailError      =Tools.checkEmail(mEmailFieldText);
        passwordError   =Tools.checkTextField(mPasswordFieldText);

        if(!emailError && !passwordError){
            return true;
        }else{
            setEmailError(emailError);
            setPasswordError(passwordError);
            return false;
        }
    }

    /**
     * Google
     * OnactivityResult event call
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginFacebookPresenter.doOnActivityResult(mCallbackManager,requestCode, resultCode, data);
        loginTwitterPresenter.doOnActivityResult(mCallbackManager,requestCode, resultCode, data);
        loginGooglePresenter.doOnActivityResult(requestCode, resultCode, data);
    }

    /**
     * Build a GoogleSignInClient with the options specified by gso.
     */
    @Override
    public GoogleSignInClient returnClient(GoogleSignInOptions gso){
        return GoogleSignIn.getClient(this, gso);
    }
    // Check for existing Google Sign In account, if the user is already signed in
    // the GoogleSignInAccount will be non-null.

    @Override
    public GoogleSignInAccount returnLastSignedAccount(){
        return GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    public String getDefaultWebClientId(){
        return getString(R.string.default_web_client_id);
    }

    @Override
    public TwitterLoginButton getTwitterButton(){
        return mLoginTwitterButton;
    }

    @Override
    public void changeActivityForResult(Intent signInIntent){
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    @Override
    public LoginFirebasePresenter getLoginFirebasePresenter(){
        return loginFirebasePresenter;
    }

    @Override
    public LoginContract.LoginFacebookPresenter getLoginFacebookPresenter() {
        return loginFacebookPresenter;
    }

    @Override
    public LoginContract.LoginGooglePresenter getLoginGooglePresenter() {
        return loginGooglePresenter;
    }

    @Override
    public LoginContract.LoginTwitterPresenter getLoginTwitterPresenter() {
        return loginTwitterPresenter;
    }

    @Override
    public Context getActivityContext(){
        return getApplicationContext();
    }

    @Override
    public void setLoadingPanelVisible(){
        mLoadingPanel.setVisibility(View.VISIBLE);
    }
    @Override
    public void setLoadingPanelGone(){
        mLoadingPanel.setVisibility(View.GONE);
    }

    public void setEmailError(Boolean error) {
        if(error) mEmailFieldLayout.setError(getString(R.string.error_invalid_email));
        else mEmailFieldLayout.setError(null);
    }

    public void setPasswordError(Boolean error) {
        if(error) mPasswordFieldLayout.setError(getString(R.string.error_invalid_password));
        else mEmailFieldLayout.setError(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        setLoadingPanelGone();
    }
}
