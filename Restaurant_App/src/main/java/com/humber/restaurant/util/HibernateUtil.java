package com.humber.restaurant.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // Use a volatile keyword for ensuring thread safety
    private static volatile SessionFactory sessionFactory;

    // Private constructor to prevent instantiation
    private HibernateUtil() {}

    // Thread-safe and lazy initialization of the SessionFactory
    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                synchronized (HibernateUtil.class) {
                    if (sessionFactory == null) {
                        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                    }
                }
            }
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Public method to get the SessionFactory
    public static SessionFactory getSessionFactory() {
        return buildSessionFactory();
    }

    // Optional method for safely closing the SessionFactory when application shuts down
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
