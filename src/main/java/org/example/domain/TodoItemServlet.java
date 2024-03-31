package org.example.domain;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class TodoItemServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private TodoItemDAO todoItemDao;

    public void init() {
        todoItemDao = new TodoItemDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertItem(request, response);
                    break;
                case "/delete":
                    deleteItem(request, response);
                    break;
                default:
                    listItems(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listItems(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List < TodoItem > listItems = todoItemDao.getAllItems();
        request.setAttribute("listItems", listItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
        dispatcher.forward(request, response);

    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-add-item-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String description = request.getParameter("description");
        Boolean status = Boolean.valueOf(request.getParameter("status"));
        TodoItem newItem = new TodoItem(description, status);
        todoItemDao.addItem(newItem);
        response.sendRedirect("list");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        todoItemDao.deleteItem(id);
        response.sendRedirect("list");

    }
}
