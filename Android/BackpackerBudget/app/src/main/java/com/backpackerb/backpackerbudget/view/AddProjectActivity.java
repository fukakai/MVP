package com.backpackerb.backpackerbudget.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.AddProjectContract;
import com.backpackerb.backpackerbudget.presenter.AddProjectPresenter;
import com.backpackerb.backpackerbudget.utils.Tools;

public class AddProjectActivity extends AppCompatActivity implements AddProjectContract.view {
    private RelativeLayout mLoadingPanel;
    private AddProjectPresenter addProjectPresenter;

    private Button addProjectButton;

    private TextInputEditText mAddProject;
    private String mAddProjectText;
    private TextInputLayout layoutAddProject;
    private Boolean addProjectError;

    /**
     * TODO: improve the activity with much more options to create the project
     * TODO: Check if the activity is auto scrolling with a lot of projects
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        final AddProjectPresenter addProjectPresenter = new AddProjectPresenter(this);

        addProjectButton    = findViewById(R.id.addProjectButton);
        addProjectButton    = findViewById(R.id.addProjectButton);
        mLoadingPanel       = findViewById(R.id.loadingPanel);
        mAddProject         = findViewById(R.id.addProjectTitle);
        layoutAddProject    = findViewById(R.id.layoutAddProjectTitle);

        addProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFields()) {
                    addProjectPresenter.addProject(mAddProjectText);
                }
            }
        });

    }
    public Boolean checkFields(){
        mAddProjectText  = mAddProject.getText().toString();
        addProjectError  = Tools.checkTextField(mAddProjectText);

        if(!addProjectError){ return true; }else {;
            setLayoutAddTitle(addProjectError);
            return false;
        }
    }

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
    public void setLayoutAddTitle(Boolean error) {
        if(error) layoutAddProject.setError(getString(R.string.error_incorrect_field));
        else layoutAddProject.setError(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        setLoadingPanelGone();
        finish(); //TODO: fin a l'activité en finish, ou, rafraishir l'id du user dans les variables, car elle restent
    }
}
