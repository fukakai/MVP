package fr.dalichamp.romain.dao;

import fr.dalichamp.romain.entities.aUser;
import fr.dalichamp.romain.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UserDao implements UserRepository<aUser>{

    @Override
    public List<aUser> getAll(Session session) {
        try{
            session.beginTransaction();
            List<aUser> users = session.createCriteria(aUser.class).list();
            session.getTransaction().commit();
            return users;
        }catch(Exception e){
            throw new HibernateException(e);
        }
    }

    @Override
    public aUser create(Session session,aUser user) {
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return user;
        }catch(Exception e){
            throw new HibernateException(e);
        }
    }

    @Override
    public Boolean deleteById(Session session,Long id) {
        try{
            System.out.println("======================> ID = "+id);
            session.beginTransaction();
            aUser user = (aUser)session.load(aUser.class,id);
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
            //session.flush() ;
            return true;
        }catch(Exception e){
            throw new HibernateException(e);
        }
    }

    @Override
    public aUser getById(Session session,String id) {
        try{
            aUser user = (aUser)session.load(aUser.class,id);
            return user;
        }catch(Exception e){
            throw new HibernateException(e);
        }
    }
}
