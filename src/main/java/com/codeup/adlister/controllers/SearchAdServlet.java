package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.SearchAdServlet", urlPatterns = "/search_ads")
public class SearchAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/profile");
            return;
        }
               request.getRequestDispatcher("/WEB-INF/search_ads.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searched_ad = request.getParameter("searched_ads");

            if (searched_ad == null) {
            response.sendRedirect("/search_ads");
            return;
        }
        request.setAttribute("searched_ads", DaoFactory.getAdsDao().findByTitle(searched_ad));
        request.getRequestDispatcher("/WEB-INF/ads/searched.jsp").forward(request, response);

    }
}