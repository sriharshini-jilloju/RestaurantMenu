<%@ page import="com.humber.restaurant.dao.MenuItemDAO" %>
<%@ page import="com.humber.restaurant.model.MenuItem" %>
<%@ page import="com.humber.restaurant.dao.CategoryDAO" %>
<%@ page import="com.humber.restaurant.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>The Velvet Fork - Menu</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <h1>The Velvet Fork</h1>
    <h2>MENU</h2>
    
    <!-- Category Selection Form -->
    <h2>Select a Category</h2>
    <form action="menu.jsp" method="GET">
        <label for="category">Choose a Category:</label>
        <select name="categoryId" id="category">
            <option value="">--Select Category--</option>
            <%
                CategoryDAO categoryDAO = new CategoryDAO();
                List<Category> categories = categoryDAO.getAllCategories();
                if (categories == null) categories = new ArrayList<>();
                for (Category category : categories) {
            %>
                <option value="<%= category.getId() %>"><%= category.getName() %></option>
            <%
                }
            %>
        </select>
        <button type="submit">Filter</button>
    </form>

    <!-- Display Menu Items Based on Selected Category -->
    <h2>Recipes</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        <%
            String categoryIdParam = request.getParameter("categoryId");
            MenuItemDAO menuItemDAO = new MenuItemDAO();
            List<MenuItem> menuItems;

            if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
                // Filter by selected category
                int categoryId = Integer.parseInt(categoryIdParam);
                menuItems = menuItemDAO.getMenuItemsByCategory(categoryId);
            } else {
                // No category selected, show all menu items
                menuItems = menuItemDAO.getAllMenuItems();
            }

            if (menuItems == null) menuItems = new ArrayList<>();
            for (MenuItem item : menuItems) {
        %>
        <tr>
            <td><%= item.getName() %></td>
            <td><%= item.getDescription() %></td>
            <td>$<%= item.getPrice() %></td>
            <td><%= (item.getCategory() != null) ? item.getCategory().getName() : "Uncategorized" %></td>
            <td>
                <!-- Action links styled as buttons -->
                <a class="action-btn edit" href="editMenuItem.jsp?id=<%= item.getId() %>">Edit</a> |
                <a class="action-btn delete" href="deleteMenuItem.jsp?id=<%= item.getId() %>">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <!-- Add New Recipe Button Styled -->
    <a class="action-btn add-new" href="addMenuItem.jsp">Add New Recipe</a>
    <footer>
    <footer>
    <%@ taglib prefix="date" uri="/WEB-INF/mydatalib.tld" %>
    <date:DisplayCurrentTime />
    
</footer>

</footer>

</body>
</html>
