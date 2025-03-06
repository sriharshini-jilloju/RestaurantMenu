package com.humber.restaurant.dao;

import com.humber.restaurant.model.Category;
import com.humber.restaurant.model.MenuItem;
import com.humber.restaurant.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class MenuItemDAO {

    // Save a new menu item
    public void saveMenuItem(MenuItem menuItem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(menuItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();  // Log the exception in production
        }
    }
 // Get menu items by category
    public List<MenuItem> getMenuItemsByCategory(int categoryId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL query to fetch menu items for a specific category
            String hql = "FROM MenuItem WHERE category.id = :categoryId";
            Query<MenuItem> query = session.createQuery(hql, MenuItem.class);
            query.setParameter("categoryId", categoryId);
            List<MenuItem> menuItems = query.list();
            return (menuItems != null) ? menuItems : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception in production
            return Collections.emptyList();
        }
    }


    // Get all menu items
    public List<MenuItem> getAllMenuItems() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<MenuItem> menuItems = session.createQuery("from MenuItem", MenuItem.class).list();
            return (menuItems != null) ? menuItems : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception in production
            return Collections.emptyList();
        }
    }
    

    // Get a menu item by its ID
    public MenuItem getMenuItemById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(MenuItem.class, id);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception in production
            return null;
        }
    }

    // Update an existing menu item
    public void updateMenuItem(int id, String name, String description, double price, int categoryId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MenuItem menuItem = session.get(MenuItem.class, id);
            if (menuItem != null) {
                menuItem.setName(name);
                menuItem.setDescription(description);
                menuItem.setPrice(price);
                CategoryDAO categoryDAO = new CategoryDAO();
                Category category = categoryDAO.getCategoryById(categoryId);
                if (category != null) {
                    menuItem.setCategory(category);
                }
                session.update(menuItem);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();  // Log the exception in production
        }
    }

    // Delete a menu item by its ID
    public void deleteMenuItem(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MenuItem menuItem = session.get(MenuItem.class, id);
            if (menuItem != null) {
                session.delete(menuItem);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();  // Log the exception in production
        }
    }
}
