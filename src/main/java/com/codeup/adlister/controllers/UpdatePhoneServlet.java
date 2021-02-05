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

@WebServlet(name = "UpdatePhoneServlet", urlPatterns = "/updatePhone")
public class UpdatePhoneServlet extends HttpServlet {
    User profile;
    long profileId;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedUsername = request.getPathInfo();
        try {
            profileId = Long.parseLong(selectedUsername.substring(1));
            profile = DaoFactory.getUsersDao().findById(profileId);
            request.setAttribute("user", profile);
            request.getRequestDispatcher("/WEB-INF/updateProfile.jsp").forward(request,response);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String newPhone = request.getParameter("phone");
        //validate input
        boolean hasInputErrors = false;

        //List of possible errors & responses
        ArrayList<String> listOfErrors = new ArrayList<>();

        //check for error
        if(newPhone.isEmpty()){
            String newPhoneIsEmpty = "Please enter a valid Phone number.";
            listOfErrors.add(newPhoneIsEmpty);
            hasInputErrors = true;
        } else {
            User user = DaoFactory.getUsersDao().findByUsername(newPhone);
            if(user != null && !user.getPhoneNumber().equals(profile.getPhoneNumber())){
                listOfErrors.add("Sorry. That phone number is already in use. Please choose another phone number");
                hasInputErrors = true;
            }
        }
        if(hasInputErrors){
            request.setAttribute("listOfErrors", listOfErrors);
            request.getRequestDispatcher("/WEB-INF/updateProfile.jsp").forward(request, response);
        } else {
            DaoFactory.getUsersDao().updatePhone(newPhone, userId);
            response.sendRedirect("/login");
        }
    }
}

