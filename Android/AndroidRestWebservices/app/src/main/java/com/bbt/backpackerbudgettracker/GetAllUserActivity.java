package com.bbt.backpackerbudgettracker;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bbt.model.User;
import com.bbt.server.UserController;

public class GetAllUserActivity extends AppCompatActivity {
    private List<User> lists = null;
    private List listsName = new ArrayList();
    private ListView listLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_user);

        listLists = (ListView) findViewById(R.id.listLists);

        getUsers();
    }

    /**
     * Transform Users to an Android Layout formated List
     * Set list on the screen
     */
    final void getUsers() {

        UserController list = new UserController();
        try {
            lists = list.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (lists != null) {
            for (User user : lists) {
                if (user != null)
                    listsName.add(user.getFirstname() + " " + user.getLastname());
            }

            /* Set list on the screen */
            listLists.setAdapter(new ArrayAdapter(getBaseContext(),
                    android.R.layout.simple_list_item_1, listsName));

            listLists.setTextFilterEnabled(true);

        }
    }
}
