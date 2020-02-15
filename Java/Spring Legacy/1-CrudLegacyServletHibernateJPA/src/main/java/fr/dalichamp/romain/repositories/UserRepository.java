package fr.dalichamp.romain.repositories;

import fr.dalichamp.romain.entities.User;
import java.util.List;
import org.hibernate.Session;

/**
 * Interface for UserDao Object with Generic T
 * @param <T>
 */
public interface UserRepository <T>{
    List<User> getAll(Session session);
    User create(Session session, User user);
    Boolean deleteById(Session session,Long id) ;
    User getById(Session session,String id);
}