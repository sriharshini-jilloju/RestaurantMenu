package com.humber.restaurant.servlet;

import com.humber.restaurant.dao.CategoryDAO;
import com.humber.restaurant.dao.MenuItemDAO;
import com.humber.restaurant.model.Category;
import com.humber.restaurant.model.MenuItem;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

 
public class AddMenuItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the form submission
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        // Retrieve category by ID
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = categoryDAO.getCategoryById(categoryId);

        // Check if the category exists
        if (category != null) {
            // Create a new MenuItem and set its properties
            MenuItem menuItem = new MenuItem();
            menuItem.setName(name);
            menuItem.setDescription(description);
            menuItem.setPrice(price);
            menuItem.setCategory(category);

            // Save the MenuItem
            MenuItemDAO menuItemDAO = new MenuItemDAO();
            menuItemDAO.saveMenuItem(menuItem);  // Add Menu Item

            // Use RequestDispatcher to forward the request to addMenuItem.jsp
            response.sendRedirect(request.getContextPath() + "/jsp/menu.jsp");
        } else {
            // Handle the case where the category is not found (optional)
            response.getWriter().write("Category not found.");
        }
    }
}
