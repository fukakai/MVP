package fr.dalichamp.romain.servicies;

import fr.dalichamp.romain.dao.UserDao;
import fr.dalichamp.romain.entities.aUser;
import fr.dalichamp.romain.repositories.UserRepository;
import fr.dalichamp.romain.utilities.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UserService {

    private UserRepository userRepository;

    public UserService(){
        userRepository = new UserDao();
    }

    /* Get User List */
    public List<aUser> getUserList(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<aUser> userlist = userRepository.getAll(session);
            session.close();
            return userlist;
        }catch(HibernateException e){
            throw new HibernateException(e);
        }
    }

    /* Add User */
    public boolean addUser(aUser user){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            userRepository.create(session, user);
            session.close();
            return true;
        }catch(HibernateException e){
            throw new HibernateException(e);
        }
    }

    /* Delete User */
    public boolean delUser(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("======================> User Service ID = "+id);
            userRepository.deleteById(session,id);
            session.close();
            return true;
        }catch(HibernateException e){
            throw new HibernateException(e);
        }
    }
}
