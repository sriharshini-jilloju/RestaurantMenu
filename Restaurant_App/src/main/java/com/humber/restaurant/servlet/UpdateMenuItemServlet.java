package com.humber.restaurant.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.humber.restaurant.dao.MenuItemDAO;


public class UpdateMenuItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        MenuItemDAO menuItemDAO = new MenuItemDAO();
        menuItemDAO.updateMenuItem(id, name, description, price, categoryId);

        response.sendRedirect(request.getContextPath() + "/jsp/menu.jsp");

    }
}

