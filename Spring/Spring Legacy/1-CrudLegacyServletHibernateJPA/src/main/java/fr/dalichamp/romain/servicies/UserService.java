package fr.dalichamp.romain.servicies;

import fr.dalichamp.romain.dao.UserDao;
import fr.dalichamp.romain.entities.User;
import fr.dalichamp.romain.repositories.UserRepository;
import fr.dalichamp.romain.utilities.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * This class is user the User Repository to call user Data Access Object methods
 * Every methods inits and close a session with Hibernate
 */
public class UserService {
    private UserRepository userRepository;

    public UserService(){
        userRepository = new UserDao();
    }

    /**
     * Open Hibernate session to get a userList from database
     * @return
     */
    public List<User> getUserList(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<User> userlist = userRepository.getAll(session);
            session.close();
            return userlist;
        }catch(HibernateException e){
            throw new HibernateException(e);
        }
    }

    /**
     * Open Hibernate Session to add a user with the User object given in parameter
     * @param user
     * @return
     */
    public boolean addUser(User user){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            userRepository.create(session, user);
            session.close();
            return true;
        }catch(HibernateException e){
            throw new HibernateException(e);
        }
    }

    /**
     * Open hibernate session to delete a user from database with the Long id given in database
     * @param id
     * @return
     */
    public boolean delUser(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            userRepository.deleteById(session,id);
            session.close();
            return true;
        }catch(HibernateException e){
            throw new HibernateException(e);
        }
    }
}
