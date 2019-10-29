package fr.dalichamp.romain.repositories;

import fr.dalichamp.romain.entities.aUser;
import java.util.List;
import org.hibernate.Session;

public interface UserRepository <T>{
    List<aUser> getAll(Session session);
    aUser create(Session session,aUser user);
    Boolean deleteById(Session session,Long id) ;
    aUser getById(Session session,String id);
}