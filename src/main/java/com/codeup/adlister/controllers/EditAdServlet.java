package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@WebServlet(name = "EditAdServlet", urlPatterns = "/editAd")
public class EditAdServlet extends HttpServlet {
    Ad ad;
    long adId;


//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String selectedAd = request.getPathInfo();
//        try {
//            adId = Long.parseLong(selectedAd.substring(1));
//            ad = DaoFactory.getAdsDao().findByTitle(selectedAd);
//            request.setAttribute("user", profile);
//            request.getRequestDispatcher("/WEB-INF/updateProfile.jsp").forward(request,response);
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
//        }
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}