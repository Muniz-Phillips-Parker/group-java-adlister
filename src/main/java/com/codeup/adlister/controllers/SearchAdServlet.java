package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/search_ads")
public class SearchAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               request.getRequestDispatcher("/WEB-INF/search_ads.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String searched_ad = request.getParameter("searched_ads");

        boolean inputHasErrors = searched_ad.isEmpty();

        if (inputHasErrors) {
            response.sendRedirect("/search_ads");
            return;
        }
        request.setAttribute("ads", DaoFactory.getAdsDao().all());

    }
}