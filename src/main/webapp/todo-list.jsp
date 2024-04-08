
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>Todo List</h1>
<table border="1">
    <tr>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="todoitem" items="${listItems}">
        <tr>
            <td>${todoitem.description}</td>
            <td>
                <a href="delete?id=${todoitem.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="new">Add Item</a>
</body>
</html>

