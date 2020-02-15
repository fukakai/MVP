package fr.dalichamp.romain.servicies;

import fr.dalichamp.romain.entities.aUser;
import fr.dalichamp.romain.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /* Get User List */
    public List<aUser> getUserList(){
        return userRepository.findAll();
    }

    /* Add User */
    public boolean addUser(aUser user){
        try{
            userRepository.save(user);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /* Delete User */
    public boolean delUser(Long id){
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
