package fr.dalichamp.romain.repositories;

import fr.dalichamp.romain.entities.User;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Interface for UserDao Object with Generic T
 * @param <User>
 */
@Repository
public interface UserRepository <User>{
    List<User> getAll(Session session);
    User create(Session session, User user);
    Boolean deleteById(Session session,Long id) ;
    User getById(Session session,String id);
}