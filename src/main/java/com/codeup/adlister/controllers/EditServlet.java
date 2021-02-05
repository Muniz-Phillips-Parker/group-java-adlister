package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "controllers.EditServlet", urlPatterns = "/ads/edit")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        long id = Long.parseLong(request.getParameter("id"));
        Ad ad = DaoFactory.getAdsDao().findById(id);
        request.setAttribute("id", id);
        request.setAttribute("ad", ad);

        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newTitle = request.getParameter("title");
        String newDescription = request.getParameter("description");
        Long adId = Long.parseLong(request.getParameter("ad-id"));
//        long newId = Long.parseLong(request.getParameter("id"));

        User user = (User) request.getSession().getAttribute("user");
        Ad ad = (Ad) request.getSession().getAttribute("ad");

        DaoFactory.getAdsDao().editAd(newTitle, newDescription, adId);
        response.sendRedirect("/profile");
    }
}
