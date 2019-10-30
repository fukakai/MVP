package fr.dalichamp.romain.dao;

import fr.dalichamp.romain.entities.User;
import fr.dalichamp.romain.repositories.UserRepository;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * This class implements UserRespository to define its structure
 * Using the Hibernate "session" parameter, each methods starts and close a transaction
 * to execute and commit operations on datase
 */
public class UserDao implements UserRepository<User>{

    /**
     * Return a list of users
     * @param session
     * @return
     */
    @Override
    public List<User> getAll(Session session) {
        try{
            session.beginTransaction();
            List<User> users = session.createCriteria(User.class).list();
            session.getTransaction().commit();
            return users;
        }catch(Exception e){
            throw new HibernateException(e);
        }
    }

    /**
     * Create and return the user given in parameter
     * @param session
     * @param user
     * @return
     */
    @Override
    public User create(Session session, User user) {
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return user;
        }catch(Exception e){
            throw new HibernateException(e);
        }
    }

    /**
     * Delete a user with its ID given in parameter
     * @param session
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Session session,Long id) {
        try{
            System.out.println("======================> ID = "+id);
            session.beginTransaction();
            User user = (User)session.load(User.class,id);
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
            //session.flush() ;
            return true;
        }catch(Exception e){
            throw new HibernateException(e);
        }
    }

    /**
     * Return a user find with its id given in parameter
     * @param session
     * @param id
     * @return
     */
    @Override
    public User getById(Session session,String id) {
        try{
            User user = (User)session.load(User.class,id);
            return user;
        }catch(Exception e){
            throw new HibernateException(e);
        }
    }
}
