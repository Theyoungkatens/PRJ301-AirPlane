<%-- 
    Document   : productList
    Created on : Oct 17, 2023, 11:21:23 PM
    Author     : Zane
--%>

<%@page import="java.util.List"%>
<%@page import="dto.Products"%>
<%@page import="dto.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            Users loginUser = (Users) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !("AD".equals(loginUser.getRoleID()))) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <a href="MainController?action=Logout" class="Logout">Logout</a>    
        <form action ="MainController">
            <input type ="text" placeholder="Search by ID " name ="search" value="<%=search%>" />
            <input type ="submit" name ="action" value ="Search" />
        </form>
        <%
            List<Products> listPro = (List<Products>) request.getAttribute("LIST_PRODUCT");
            if (listPro != null) {
                if (listPro.size() > 0) {
        %>
        </br>
        <table class="table" border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Update</th>
                    
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Products c : listPro) {
                %>

            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td><%= c.getId()%></td>
                    <td>
                        <input type="text" name="name" value="<%= c.getName()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="description" value="<%= c.getDescription()%>" required=""/>
                    </td>
                    <td>
                        <input type="number" name="quantity" value="<%= c.getQuantity() %>" required=""/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="id" value="<%= c.getId()%>"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                    <td>
                        <a href="MainController?search=<%=search%>&action=Delete&id=<%=c.getId()%>">Delete</a>
                    </td>
                </tr>
            </form>

            <%
                }
            %>
        </tbody>
    </table>
    <div class="error-message">
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <%= error%>
    </div>

    <%
            }
        }
    %>
</body>
<form action="productList.jsp" method="POST">
    <input type="submit" name="status" value="add">
</form>
<% String status = request.getParameter("status");
    if (status != null && status.equals("add")) {
%>
<h1>Add New Inventory</h1>
<form action="MainController" method="POST">
    ID <input type="text" name="id" required=""><br>
    Name <input type="text" name="name" required=""><br>
    Major <input type="text" name="major" required=""><br>
    Year Of Birth <input type="number" name="yearOfBirth" required=""><br>
    Gender <select name="gender" >
        <option value="M" >Male</option>
        <option value="FM" >Female</option>
    </select><br>
    Year of Admission <input type="number" name="yearofadmission" required=""><br>

    <input type="submit" name="action" value="save">
</form>
<%}%>
<p style="background-color: green; margin-top:20px;color:white;display:inline-block;">${noti}</p>
</html>
