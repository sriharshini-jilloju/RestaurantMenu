package com.humber.restaurant.servlet;

import com.humber.restaurant.model.MenuItem;
import com.humber.restaurant.util.HibernateUtil; // Utility class to get session factory
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteMenuItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            MenuItem item = session.get(MenuItem.class, id);
            if (item != null) {
                session.delete(item);
                tx.commit();
                response.sendRedirect(request.getContextPath() + "/jsp/menu.jsp"); 
            } else {
                response.getWriter().write("Item not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error deleting item");
        }
    }
}
