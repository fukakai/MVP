package com.backpackerb.backpackerbudget.contract;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.widget.ArrayAdapter;

import com.backpackerb.backpackerbudget.model.Project;
import com.backpackerb.backpackerbudget.model.ProjectList;
import com.backpackerb.backpackerbudget.model.User;

public interface HomeContract {

    interface view{
        public Context getActivityContext();
        public void updateUserProfilePictureInfos(User user);
        public void updateProjectList(ArrayAdapter<Project> projectListAdapter);
    }

    interface presenter{
    }
}
