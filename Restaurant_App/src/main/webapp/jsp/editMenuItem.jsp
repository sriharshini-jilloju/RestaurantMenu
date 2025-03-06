<%@ page import="com.humber.restaurant.dao.MenuItemDAO" %>
<%@ page import="com.humber.restaurant.model.MenuItem" %>
<%@ page import="com.humber.restaurant.dao.CategoryDAO" %>
<%@ page import="com.humber.restaurant.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    int itemId = Integer.parseInt(request.getParameter("id"));
    MenuItemDAO menuItemDAO = new MenuItemDAO();
    MenuItem item = menuItemDAO.getMenuItemById(itemId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Menu Item</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/custom.css">
</head>
<body>
    <h1>Edit Menu Item</h1>

    <form action="<%= request.getContextPath() %>/editMenuItem" method="post">
        <input type="hidden" name="id" value="<%= item.getId() %>">

        <div class="form-group">
            <label for="name">Item Name:</label>
            <input type="text" id="name" name="name" value="<%= item.getName() %>" required>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="<%= item.getDescription() %>" required>
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" step="0.01" id="price" name="price" value="<%= item.getPrice() %>" required>
        </div>

        <div class="form-group">
            <label for="category">Category:</label>
            <select id="category" name="categoryId" required>
                <%
                    CategoryDAO categoryDAO = new CategoryDAO();
                    List<Category> categories = categoryDAO.getAllCategories();
                    for (Category category : categories) {
                        String selected = (category.getId() == item.getCategory().getId()) ? "selected" : "";
                %>
                <option value="<%= category.getId() %>" <%= selected %>><%= category.getName() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <button type="submit">Update Menu Item</button>
        </div>
    </form>

    <a href="<%= request.getContextPath() %>/jsp/menu.jsp" class="add-new">Back to Menu</a>
</body>
</html>
