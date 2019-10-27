package com.backpackerb.backpackerbudget.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.HomeContract;
import com.backpackerb.backpackerbudget.model.Project;
import com.backpackerb.backpackerbudget.model.ProjectList;
import com.backpackerb.backpackerbudget.model.User;
import com.backpackerb.backpackerbudget.presenter.HomePresenter;
import com.backpackerb.backpackerbudget.presenter.SocialLogoutPresenter;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.facebook.login.widget.ProfilePictureView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeContract.view {
    private HomePresenter homePresenter;
    private OnClickClass onClickClass;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View headerLayout;
    private ProfilePictureView facebookProfilePicture;
    private ImageView googleProfilePicture;
    private ImageView twitterProfilePicture;
    private ImageView firebaseProfilePicture;
    private TextView fullnameView;
    private TextView emailView;
    private ListView projectListView;
    private ProjectList projectlist = new ProjectList();
    private View appBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //presenters
        homePresenter = new HomePresenter(this);

        //UsedMethods
        onClickClass = new OnClickClass();

        //ids
        toolbar                 = findViewById(R.id.toolbar);
        drawer                  = findViewById(R.id.drawer_layout);
        navigationView          = findViewById(R.id.nav_view);
        appBarView              = findViewById(R.id.fab);
        headerLayout            = navigationView.getHeaderView(0);
        facebookProfilePicture  = headerLayout.findViewById(R.id.facebookProfilePicture);
        googleProfilePicture    = headerLayout.findViewById(R.id.googleProfilePicture);
        twitterProfilePicture   = headerLayout.findViewById(R.id.twitterProfilePicture);
        firebaseProfilePicture  = headerLayout.findViewById(R.id.firebaseProfilePicture);
        fullnameView            = headerLayout.findViewById(R.id.fullnameView);
        emailView               = headerLayout.findViewById(R.id.emailView);
        projectListView         = findViewById(R.id.projectListView);


        //sets
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //Navigation Header & Drawer
        appBarView.setOnClickListener(onClickClass);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Onclick Class for MVP Refactoring
     */
    public class OnClickClass implements View.OnClickListener{
        @Override
        public void onClick(final View view){
            switch (view.getId()){
                case R.id.fab: Tools.changeActivity(getActivityContext(),AddProjectActivity.class); break;
            }
        }
    }

    /**
     * TODO: Onclick on each item and send to another activity with a list of all the transactions
     * TODO: add a X to erase a project (with all details, in a moment)
     * TODO: in the navigation, create a menu & activity to connect the user to the banks API
     * @param projectListAdapter
     */
    public void updateProjectList(ArrayAdapter<Project> projectListAdapter){
        projectListView.setAdapter(projectListAdapter);
    }

    /**
     * Update User profile picture infos
     * @param user
     */
    public void updateUserProfilePictureInfos(User user) {
        fullnameView.setText(user.getFullName());
        emailView.setText(user.getEmail());
        selectProfilePicture(user);
    }

    /**
     * Select the good profile picture code to show
     * @param user
     */
    public void selectProfilePicture(User user){
        facebookProfilePicture.setVisibility(View.GONE);
        googleProfilePicture.setVisibility(View.GONE);
        twitterProfilePicture.setVisibility(View.GONE);
        firebaseProfilePicture.setVisibility(View.GONE);

        Log.w(Constants.LOG_HOME,"User network: "+user.getNetwork());
        switch(user.getNetwork()){
            case Constants.TAG_FACEBOOK:
                facebookProfilePicture.setVisibility(View.VISIBLE);
                facebookProfilePicture.setProfileId(user.getId()); break;
            case Constants.TAG_GOOGLE:
                googleProfilePicture.setVisibility(View.VISIBLE);
                Picasso.get().load(user.getPicture()).into(googleProfilePicture);break;
            case Constants.TAG_TWITTER:
                twitterProfilePicture.setVisibility(View.VISIBLE);
                Picasso.get().load(user.getPicture()).into(twitterProfilePicture);break;
            case Constants.TAG_FIREBASE:
                firebaseProfilePicture.setVisibility(View.VISIBLE); break;
        }
    }

    @Override
    public Context getActivityContext(){
        return getApplicationContext();
    }

    ///////////////////ANDROID STUDIO GENERATED//////////////

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_bank:Tools.changeActivity(getActivityContext(),AddBank.class);break;// Handle the camera action
            case R.id.nav_slideshow:break;
            case R.id.nav_manage:break;
            case R.id.nav_share:break;
            case R.id.nav_logout:
                SocialLogoutPresenter.staticLogOutOn();
                Tools.changeActivity(getApplicationContext(), LoginActivity.class, "go to login activity");
                finish(); //Important finish, if the user log with another method, we need to start the activity again to load all the userinfos in the presenter
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
