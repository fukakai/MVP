package com.backpackerb.backpackerbudget.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.backpackerb.backpackerbudget.presenter.SocialLogoutPresenter;
import com.backpackerb.backpackerbudget.view.HomeActivity;
import com.backpackerb.backpackerbudget.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Tools {

    public static void changeActivity(Context activityContext, Class parameterClass,String log_message){
        Log.w(Constants.LOG_TOOLS,log_message);

        Intent intent = new Intent(activityContext, parameterClass);
        activityContext.startActivity(intent);
    }

    public static void changeActivity(Context activityContext, Class parameterClass){
        Tools.changeActivity(activityContext,parameterClass,"Change Activity from: "+activityContext.getClass().toString()+" to: "+parameterClass.toString());
    }

    public static void toastMessage(Context context,String constant){
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        Toast.makeText( context, constant, Toast.LENGTH_SHORT).show();
    }

    public static void checkIfUserIsConnected(Context activityContext, String log, Class activityDestination){
        //else we stock locally the user to use the informations
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            Log.w(Constants.LOG_TOOLS,"USER IS NULL");
            SocialLogoutPresenter.staticLogOutOn();
            Tools.changeActivity(activityContext,LoginActivity.class);
        }else{
            //check is this is useless
            if(activityDestination != null) {
                Tools.changeActivity(activityContext, activityDestination);
            }
        }
    }
    public static void checkIfUserIsConnected(Context activityContext, String log){
        Tools.checkIfUserIsConnected(activityContext,activityContext.getClass().toString(),null);
    }

    public static void checkIfUserIsConnected(Context activityContext){
        Tools.checkIfUserIsConnected(activityContext,activityContext.getClass().toString());
    }

    public static void checkIfUserIsLoggedOut(Context activityContext){
        //else we stock locally the user to use the informations
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            //When the user is connected and is on an activity where it require to be logged, out by security
            //we redirect him to the main activity HomeActivity
            Tools.changeActivity(activityContext,HomeActivity.class);
        }
    }

    /**
     * Boolean result for emailError={result}
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        return TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Boolean result for fieldError={result}
     * @param text
     * @return
     */
    public static Boolean checkTextField(String text){
        if(text.isEmpty() || text.length() <= 3){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Boolean result for passwordsError={result}
     * @param password1
     * @param password2
     * @return
     */
    public static boolean checkPasswords(String password1, String password2){
        return !password1.equals(password2);
    }
}
