package com.backpackerb.backpackerbudget.contract;

import android.content.Context;
import android.content.Intent;

import com.backpackerb.backpackerbudget.model.User;

public interface RegisterContract {

    interface view{
        Intent getIntentRegister();

        Context getActivityContext();

        void setLoadingPanelVisible();

        void setLoadingPanelGone();

        void setLayoutRegisterEmailAdress(Boolean error);

        void setLayoutRegisterFirstname(Boolean error);

        void setLayoutRegisterLastname(Boolean error);

        void setLayoutRegisterPassword1(Boolean error);

        void setLayoutRegisterPassword2(Boolean error);
    }

    interface presenter{
        void registerUser(String email, String firstname, String lastname, String password1, String password2);
        void createUser();
        boolean checkPassword();
    }
}
