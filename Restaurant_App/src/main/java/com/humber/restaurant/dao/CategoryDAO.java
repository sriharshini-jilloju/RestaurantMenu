package com.humber.restaurant.dao;

import com.humber.restaurant.model.Category;
import com.humber.restaurant.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class CategoryDAO {

    // Save a new category
    @SuppressWarnings("deprecation")
	public void saveCategory(Category category) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();  // Log the exception in production
        }
    }

    // Get all categories
    public List<Category> getAllCategories() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Category> categories = session.createQuery("from Category", Category.class).list();
            return (categories != null) ? categories : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception in production
            return Collections.emptyList();
        }
    }

    // Get a category by its ID
    public Category getCategoryById(int categoryId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, categoryId);  // Fetch category by ID
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception in production
            return null;
        }
    }
}
