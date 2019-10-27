package com.backpackerb.backpackerbudget.presenter;

import android.support.annotation.NonNull;
import com.backpackerb.backpackerbudget.R;
import com.backpackerb.backpackerbudget.contract.RegisterContract;
import com.backpackerb.backpackerbudget.model.User;
import com.backpackerb.backpackerbudget.utils.Constants;
import com.backpackerb.backpackerbudget.utils.Tools;
import com.backpackerb.backpackerbudget.view.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPresenter implements RegisterContract.presenter {

    private final RegisterContract.view view;
    private final FirebaseAuth mAuth;

    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(Constants.TAG_FIREBASE_REALTIME_DATABASE_USERS);
    private User user = new User();

    public RegisterPresenter(RegisterContract.view view) {
        this.view = view;
        this.mAuth = FirebaseAuth.getInstance();

        //Check user Authentication
        Tools.checkIfUserIsLoggedOut(view.getActivityContext());
    }

    /**
     * Set User as user and call Createuser Method
     * @param email
     * @param firstname
     * @param lastname
     * @param password1
     * @param password2
     */
    @Override
    public void registerUser(String email, String firstname, String lastname, String password1, String password2) {
        user.setNetwork(Constants.TAG_FIREBASE);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword1(password1);
        user.setPassword2(password2);

        createUser();
    }

    /**
     * Create Firebase.User with Model.User
     */
    @Override
    public void createUser(){
        view.setLoadingPanelVisible();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword1())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            //FirebaseUser.getUid is the database key to retreive the user
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            user.setId(firebaseUser.getUid());
                            database.child(mAuth.getUid()).setValue(user);

                            // Sign in success, update UI with the signed-in user's information
                            Tools.changeActivity(view.getActivityContext(),HomeActivity.class);

                        }else{
                            view.setLoadingPanelGone();
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_firebase_email_exists));
                            } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_firebase_incorrect_password));
                            } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_firebase_invalid_user));
                            } else {
                                Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_firebase_unknown));
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Tools.toastMessage(view.getActivityContext(),view.getActivityContext().getString(R.string.error_firebase_failed));
                    }
                });
    }

    @Override
    public boolean checkPassword(){
        if(!user.getPassword1().isEmpty() &&
                user.getPassword1().equals(user.getPassword2())){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkFirstLastName(){
        if(!user.getFirstname().isEmpty()){return true;}else{ return false;}
    }
}
