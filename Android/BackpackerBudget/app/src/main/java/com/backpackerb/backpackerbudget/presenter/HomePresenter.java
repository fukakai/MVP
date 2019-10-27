package com.backpackerb.backpackerbudget.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.backpackerb.backpackerbudget.contract.HomeContract;
import com.backpackerb.backpackerbudget.model.Project;
import com.backpackerb.backpackerbudget.model.ProjectList;
import com.backpackerb.backpackerbudget.model.User;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class HomePresenter implements HomeContract.presenter {

    private final HomeContract.view view;
    private final FirebaseAuth mAuth;
    private final FirebaseUser firebaseUser;

    //Firebase Database references
    final DatabaseReference databaseProjects = FirebaseDatabase.getInstance().getReference(Constants.TAG_FIREBASE_REALTIME_DATABASE_PROJECTS);
    final DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference(Constants.TAG_FIREBASE_REALTIME_DATABASE_USERS);

    //Project list containers
    ProjectList projectlist = new ProjectList();
    ArrayAdapter<Project> projectListViewAdapter;

    public HomePresenter(HomeContract.view view) {
        this.view = view;
        this.mAuth = FirebaseAuth.getInstance();

        //Check user Authentication
        Tools.checkIfUserIsConnected(view.getActivityContext(),this.getClass().toString());

        //set
        firebaseUser = mAuth.getCurrentUser();
        projectListViewAdapter = new ArrayAdapter<>(view.getActivityContext(),android.R.layout.simple_list_item_1,projectlist.getProjectList());

        userInfos();
        userProjectList();
    }

    /**
     * Load project list for the current user
     */
    public void userProjectList(){
        //filter projects corresponding to the current user
        Query queryRef = databaseProjects.orderByChild("id_user").equalTo(firebaseUser.getUid());

        //valueListener
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                projectListViewAdapter.clear(); //clear the project list container
                Iterable<DataSnapshot> projects = dataSnapshot.getChildren();

                //add every project to the ArrayList object
                for(DataSnapshot child: projects){
                    Project project = child.getValue(Project.class);
                    projectlist.getProjectList().add(project);
                }
                view.updateProjectList(projectListViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Facebook Profile Picture & Informations
     */
    public void userInfos(){
        Query queryRef = databaseUsers.child(firebaseUser.getUid());

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                User user = dataSnapshot.getValue(User.class);
                if(user!=null) {
                    view.updateUserProfilePictureInfos(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        };
        //Execute Firebase Database Query in SingleValueEvent mode
        queryRef.addListenerForSingleValueEvent(userListener);
    }
}
