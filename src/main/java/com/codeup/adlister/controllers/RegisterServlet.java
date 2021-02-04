package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        // validate input
        boolean inputHasErrors = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || (!password.equals(passwordConfirmation));

//        The following will check for potential errors

//        ArrayList<String> listOfErrors = new ArrayList<>();
//        //Username field blank or duplicate
//        if (username.isEmpty()) {
//            String usernameIsEmpty = "Please enter a valid username.";
//            listOfErrors.add(usernameIsEmpty);
//            inputHasErrors = true;
//        } else {
//            User user = DaoFactory.getUsersDao().findByUsername(username);
//            if (user != null) {
//                listOfErrors.add("That username has already been claimed. You need to pick a different username.");
//                inputHasErrors = true;
//            }
//
//            if (inputHasErrors) {
//                response.sendRedirect("/register");
//                return;
//            }
//        }
////        Now we will check to see if the email field is empty or already in use.
//        if(email.isEmpty()){
//            String emailIsEmpty = "Please enter a valid email address.";
//            listOfErrors.add(emailIsEmpty);
//            inputHasErrors = true;
//        } else {
//            User user = DaoFactory.getUsersDao().findByEmail(email);
//            if(user != null){
//                listOfErrors.add("An account with that email address is already in use. Please login or choose another email address.");
//                inputHasErrors = true;
//            }
//        }
//        if(password.isEmpty()){
//            String passwordIsEmpty = "Please enter a password.";
//            listOfErrors.add(passwordIsEmpty);
//            inputHasErrors = true;
//        }
//        if(!password.equals(passwordConfirmation)){
//            String passwordsDontMatch = "Your passwords don't match. Please try again.";
//            listOfErrors.add(passwordsDontMatch);
//            inputHasErrors = true;
//        }
//        if (inputHasErrors){
//            request.setAttribute("listOfErrors",listOfErrors);
//            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
//        } else {
//            User user = new User(username, email, password);
//            DaoFactory.getUsersDao().insert(user);
//            response.sendRedirect("/login");
//        }
        if(inputHasErrors){
            response.sendRedirect("/register");
            return;
        }
        // create and save a new user
        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);
        response.sendRedirect("/login");
    }
}

