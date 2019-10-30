package fr.dalichamp.romain.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    //Declare Session Factory
    private static final SessionFactory sessionFactory;
    static{
        try {
            // Hibernate loading configuration file and build session
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch(Throwable th){
            System.err.println("Enitial SessionFactory creation failed"+th);
            throw new ExceptionInInitializerError(th);
        }
    }

    /**
     * Return Hibernate session factory
     * @return
     */
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}