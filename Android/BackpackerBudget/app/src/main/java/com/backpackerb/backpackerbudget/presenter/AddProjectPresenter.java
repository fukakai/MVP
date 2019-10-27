package com.backpackerb.backpackerbudget.presenter;

import android.support.annotation.NonNull;

import com.backpackerb.backpackerbudget.contract.AddProjectContract;
import com.backpackerb.backpackerbudget.contract.RegisterContract;
import com.backpackerb.backpackerbudget.model.Project;
import com.backpackerb.backpackerbudget.model.User;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.backpackerb.backpackerbudget.view.HomeActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProjectPresenter implements AddProjectContract.presenter {

    private final AddProjectContract.view view;
    private final FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    final DatabaseReference databaseProjects = FirebaseDatabase.getInstance().getReference(Constants.TAG_FIREBASE_REALTIME_DATABASE_PROJECTS);
    private Project project = new Project();

    public AddProjectPresenter(AddProjectContract.view view) {
        this.view = view;
        this.mAuth = FirebaseAuth.getInstance();

        //Check user Authentication
        Tools.checkIfUserIsConnected(view.getActivityContext());
    }

    @Override
    public void addProject(String title) {
        firebaseUser = mAuth.getCurrentUser(); //TODO: choisir actualiser l'utilisateur ici, ou finish sur l'activité ?
        project.setId_user(firebaseUser.getUid());
        project.setTitle(title);

        view.setLoadingPanelVisible();
        //add a project with generated id (push()) and user id
        databaseProjects.push().setValue(project).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Tools.changeActivity(view.getActivityContext(),HomeActivity.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.setLoadingPanelGone();
                //Toast message
            }
        });
    }
}
