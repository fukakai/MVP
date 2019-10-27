package com.backpackerb.backpackerbudget.contract;

import android.content.Context;
import android.content.Intent;

import com.backpackerb.backpackerbudget.model.User;

public interface AddProjectContract {

    interface view{
        Intent getIntentRegister();

        Context getActivityContext();

        void setLoadingPanelVisible();

        void setLoadingPanelGone();

        void setLayoutAddTitle(Boolean error);
    }

    interface presenter{
        void addProject(String title);
    }
}
