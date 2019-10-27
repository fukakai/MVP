package com.bbt.backpackerbudgettracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bbt.model.User;
import com.bbt.server.UserController;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Button btn = (Button) findViewById(R.id.addUserNow);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    final void addUser() {

        /* This declaration have to stay outstide of the run method */
        final UserController user_controller = new UserController();

        final Thread checkUpdate = new Thread() {
            public void run() {
                EditText name       = (EditText) findViewById(R.id.userName);
                EditText firstname  = (EditText) findViewById(R.id.userFirstName);
                EditText mail       = (EditText) findViewById(R.id.userMail);
                EditText phone      = (EditText) findViewById(R.id.userPhone);

                User user = new User();
                user.setFirstname(firstname.getText().toString());
                user.setLastname(name.getText().toString());
                user.setMail(mail.getText().toString());
                user.setPhone(phone.getText().toString());

                try {
                    user_controller.create(user);
                } catch (Exception e) {
                    return;
                }
                final Intent intent = new Intent(AddUserActivity.this, GetAllUserActivity.class);
                startActivity(intent);
            }
        };
        checkUpdate.start();
    }
}
