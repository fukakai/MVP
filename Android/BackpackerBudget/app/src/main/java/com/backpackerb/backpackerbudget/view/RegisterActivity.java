package com.backpackerb.backpackerbudget.view;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.RegisterContract;
import com.backpackerb.backpackerbudget.model.User;
import com.backpackerb.backpackerbudget.presenter.RegisterPresenter;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.view  {

    private RegisterPresenter registerPresenter;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private RelativeLayout mLoadingPanel;

    private TextInputEditText mEmailField;
    private TextInputEditText mRegisterFirstname;
    private TextInputEditText mRegisterLastname;
    private TextInputEditText mPassword1Field;
    private TextInputEditText mPassword2Field;
    private String mEmailFieldText;
    private String mRegisterFirstnameText;
    private String mRegisterLastnameText;
    private String mPassword1FieldText;
    private String mPassword2FieldText;
    private TextInputLayout layoutRegisterEmailAdress;
    private TextInputLayout layoutRegisterFirstname;
    private TextInputLayout layoutRegisterLastname;
    private TextInputLayout layoutRegisterPassword1;
    private TextInputLayout layoutRegisterPassword2;
    private Boolean emailError;
    private Boolean firstnameError;
    private Boolean lastnameError;
    private Boolean password1Error;
    private Boolean password2Error;
    private Boolean passwordsError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterPresenter(this);

        Button mRegisterButton      = findViewById(R.id.mRegisterButton);
        mLoadingPanel               = findViewById(R.id.loadingPanel);

        mEmailField                 = findViewById(R.id.registerEmailAdress);
        mRegisterFirstname          = findViewById(R.id.registerFirstname);
        mRegisterLastname           = findViewById(R.id.registerLastname);
        mPassword1Field             = findViewById(R.id.registerPassword1);
        mPassword2Field             = findViewById(R.id.registerPassword2);
        layoutRegisterEmailAdress   = findViewById(R.id.layoutRegisterEmailAdress);
        layoutRegisterFirstname     = findViewById(R.id.layoutRegisterFirstname);
        layoutRegisterLastname      = findViewById(R.id.layoutRegisterLastname);
        layoutRegisterPassword1     = findViewById(R.id.layoutRegisterPassword1);
        layoutRegisterPassword2     = findViewById(R.id.layoutRegisterPassword2);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFields()) {
                    registerPresenter.registerUser(mEmailFieldText, mRegisterFirstnameText, mRegisterLastnameText, mPassword1FieldText, mPassword2FieldText);
                }
            }
        });
    }

    public Boolean checkFields(){
        mEmailFieldText         = mEmailField.getText().toString();
        mRegisterFirstnameText  = mRegisterFirstname.getText().toString();
        mRegisterLastnameText   = mRegisterLastname.getText().toString();
        mPassword1FieldText     = mPassword1Field.getText().toString();
        mPassword2FieldText     = mPassword2Field.getText().toString();

        emailError      = Tools.checkEmail(mEmailFieldText);
        firstnameError  = Tools.checkTextField(mRegisterFirstnameText);
        lastnameError   = Tools.checkTextField(mRegisterLastnameText);
        password1Error  = TextUtils.isEmpty(mPassword1FieldText);
        password2Error  = TextUtils.isEmpty(mPassword2FieldText);
        passwordsError  = Tools.checkPasswords(mPassword1FieldText,mPassword2FieldText);

        if(!emailError && !firstnameError && !lastnameError && !password1Error && !password2Error && !passwordsError) {
            return true;
        }else {

            setLayoutRegisterEmailAdress(emailError);
            setLayoutRegisterFirstname(firstnameError);
            setLayoutRegisterLastname(lastnameError);

            if(passwordsError){
                setLayoutRegisterPassword1(true);
                setLayoutRegisterPassword2(true);
            }else{
                setLayoutRegisterPassword1(password1Error);
                setLayoutRegisterPassword2(password2Error);
            }
            return false;
        }
    }
    /**
     * Return intent for email link methods in the RegisterPresenter
     * @return
     */
    @Override
    public Intent getIntentRegister(){
        return getIntent();
    }

    @Override
    public Context getActivityContext(){
        return getApplicationContext();
    }

    @Override
    public void setLoadingPanelVisible(){
        Log.w("LOGBB-LOGIN_ACTIVITY","setLoadingPanelVisible");
        mLoadingPanel.setVisibility(View.VISIBLE);
    }
    @Override
    public void setLoadingPanelGone(){
        Log.w("LOGBB-LOGIN_ACTIVITY","setLoadingPanelGone");
        mLoadingPanel.setVisibility(View.GONE);
    }

    @Override
    public void setLayoutRegisterEmailAdress(Boolean error) {
        if(error) layoutRegisterEmailAdress.setError(getString(R.string.error_incorrect_email));
        else layoutRegisterEmailAdress.setError(null);
    }

    @Override
    public void setLayoutRegisterFirstname(Boolean error) {
        if(error) layoutRegisterFirstname.setError(getString(R.string.error_incorrect_field));
        else layoutRegisterFirstname.setError(null);
    }

    @Override
    public void setLayoutRegisterLastname(Boolean error) {
        if(error) layoutRegisterLastname.setError(getString(R.string.error_incorrect_field));
        else layoutRegisterLastname.setError(null);
    }

    @Override
    public void setLayoutRegisterPassword1(Boolean error) {
        if(error) layoutRegisterPassword1.setError(getString(R.string.error_incorrect_passwords));
        else layoutRegisterPassword1.setError(null);
    }

    @Override
    public void setLayoutRegisterPassword2(Boolean error) {
        if(error) layoutRegisterPassword2.setError(getString(R.string.error_incorrect_passwords));
        else layoutRegisterPassword2.setError(null);
    }

    @Override
    public void onStop() {
        super.onStop();
        setLoadingPanelGone();
    }
}
