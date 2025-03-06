<%@ page import="com.humber.restaurant.dao.MenuItemDAO" %>
<%@ page import="com.humber.restaurant.model.MenuItem" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    // Get the menu item ID from the request
    int itemId = Integer.parseInt(request.getParameter("id"));
    MenuItemDAO menuItemDAO = new MenuItemDAO();
    MenuItem item = menuItemDAO.getMenuItemById(itemId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Menu Item</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/custom.css">  <!-- Additional overrides -->
</head>
<body class="delete-page">
    <h1>Are you sure you want to delete this menu item?</h1>
    
    <div class="item-details">
        <p><strong>Item Name:</strong> <%= item.getName() %></p>
        <p><strong>Description:</strong> <%= item.getDescription() %></p>
        <p><strong>Price:</strong> $<%= item.getPrice() %></p>
    </div>

    <!-- Form to confirm the deletion -->
    <form action="<%= request.getContextPath() %>/deleteMenuItem" method="post">
        <input type="hidden" name="id" value="<%= item.getId() %>">
        <div class="form-group">
            <button type="submit" onclick="return confirm('Are you sure you want to delete this item?')">Delete Item</button>
        </div>
    </form>

    <a href="<%= request.getContextPath() %>/jsp/menu.jsp" class="add-new">Back to Menu</a>
</body>
</html>
