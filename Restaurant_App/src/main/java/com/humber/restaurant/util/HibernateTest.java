package com.humber.restaurant.util;

import com.humber.restaurant.model.Category;
import com.humber.restaurant.model.MenuItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {

    public static void main(String[] args) {
        // Get the SessionFactory from HibernateUtil
        SessionFactory factory = HibernateUtil.getSessionFactory();

        // Create a session (use openSession instead of getCurrentSession)
        Session session = factory.openSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // Create and save a category
            Category category = new Category(1, "breakfast", null);
            session.save(category);

            // Create some menu items
            MenuItem item1 = new MenuItem("Pasta", "Delicious Italian pasta", 12.99, category);
            MenuItem item2 = new MenuItem("Burger", "Juicy beef burger", 9.99, category);

            // Save the menu items
            session.save(item1);
            session.save(item2);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Sample data added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up the session
            session.close();  // Close the session here
            HibernateUtil.shutdown();
        }
    }
}
