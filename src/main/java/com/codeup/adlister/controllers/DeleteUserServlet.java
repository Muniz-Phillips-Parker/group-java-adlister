
package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLUsersDao;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "controllers.DeleteUserServlet", urlPatterns = "/delete")
public class DeleteUserServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();

        DaoFactory.getUsersDao().destroy(userId);
        request.getSession().invalidate();
        response.sendRedirect("/register");
    }
}