<%@ page import="com.humber.restaurant.dao.CategoryDAO" %>
<%@ page import="com.humber.restaurant.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Menu Item</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/custom.css">
</head>
<body>
    <h1>Add New Menu Item</h1>
    
    <form action="<%= request.getContextPath() %>/addMenuItem" method="post">
        <div style="margin-bottom: 15px;">
            <label for="name">Item Name:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div style="margin-bottom: 15px;">
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" required>
        </div>

        <div style="margin-bottom: 15px;">
            <label for="price">Price:</label>
            <input type="number" step="0.01" id="price" name="price" min="0.01" required>
        </div>

        <div style="margin-bottom: 15px;">
            <label for="category">Category:</label>
            <select id="category" name="categoryId" required>
                <% 
                    CategoryDAO categoryDAO = new CategoryDAO();
                    List<Category> categories = categoryDAO.getAllCategories();
                    if (categories.isEmpty()) {
                %>
                    <option value="">No categories available</option>
                <% 
                    } else {
                        for (Category category : categories) {
                %>
                    <option value="<%= category.getId() %>"><%= category.getName() %></option>
                <% 
                        }
                    }
                %>
            </select>
        </div>

        <div style="margin-bottom: 15px;">
            <button type="submit">Add Menu Item</button>
        </div>
    </form>

    <a href="<%= request.getContextPath() %>/jsp/menu.jsp" class="add-new">Back to Menu</a>
</body>
</html>
