
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Todo List Management Application</title>
</head>
<body>
<center>
    <h1>Todo List</h1>
    <h2>
        <a href="new">Add New Task</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Tasks</a>

    </h2>
</center>
<div align="center">
    <c:if test="${todoitem != null}">
        <form action="update" method="post">
        </c:if>
        <c:if test="${todoitem == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <c:if test="${todoitem != null}">
                    <input type="hidden" name="id" value="<c:out value='${todoitem.id}' />" />
                </c:if>
                <tr>
                    <th>Description: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${todoitem.description}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>

