package com.humber.restaurant.servlet;

import com.humber.restaurant.dao.MenuItemDAO;
import com.humber.restaurant.model.MenuItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ViewMenuServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems();

        request.setAttribute("menuItems", menuItems);
        request.getRequestDispatcher("jsp/menu.jsp").forward(request, response);
    }
}
