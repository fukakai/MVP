package com.bbt.server;

import android.util.Log;
import java.util.List;
import com.bbt.model.EngineConfiguration;
import com.bbt.model.Container;
import com.bbt.model.User;

import org.restlet.resource.ClientResource;

public class UserController {

    /* GAE Restlet Adress */
    public final ClientResource cr = new ClientResource(EngineConfiguration.gae_path + "/rest/user");

    public UserController() {
        EngineConfiguration.getInstance();
    }

    public void create(User user) throws Exception {
        final UserControllerInterface user_controller_interface = cr.wrap(UserControllerInterface.class);

        try {
            user_controller_interface.create(user);
        } catch (Exception e) {
            Log.i("UserController", "Creation failed !");
            throw e;
        }
    }

    public List getAllUsers() {
        final UserControllerInterface user_controller_interface = cr.wrap(UserControllerInterface.class);

        Container content = user_controller_interface.getAllUsers();

        return content.getUser_list();
    }
}