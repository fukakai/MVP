package fr.dalichamp.romain.servicies;

import fr.dalichamp.romain.entities.User;
import fr.dalichamp.romain.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList(){
        return userRepository.findAll();
    }
}
